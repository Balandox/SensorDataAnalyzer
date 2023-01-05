package org.suai.restApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.suai.restApp.dto.SensorDTO;
import org.suai.restApp.exceptions.SensorNotCreatedException;
import org.suai.restApp.models.Sensor;
import org.suai.restApp.services.interfaces.SensorService;
import org.suai.restApp.util.responses.ErrorResponse;
import org.suai.restApp.util.mappers.SensorMapper;
import org.suai.restApp.util.validators.SensorValidator;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> sensorRegistration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        this.sensorValidator.validate(sensorDTO, bindingResult);

        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for(FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new SensorNotCreatedException(errorMessage.toString());
        }

        Sensor sensor = SensorMapper.convertDtoToModel(sensorDTO);
        this.sensorService.save(sensor);

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException e){
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}

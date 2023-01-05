package org.suai.restApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.suai.restApp.dto.MeasurementDTO;
import org.suai.restApp.exceptions.IncorrectMeasurementException;
import org.suai.restApp.models.Measurement;
import org.suai.restApp.models.Sensor;
import org.suai.restApp.services.interfaces.MeasurementService;
import org.suai.restApp.services.interfaces.SensorService;
import org.suai.restApp.util.responses.ErrorResponse;
import org.suai.restApp.util.mappers.MeasurementMapper;
import org.suai.restApp.util.responses.MeasurementResponse;
import org.suai.restApp.util.validators.MeasurementValidator;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    private final MeasurementValidator measurementValidator;

    private final SensorService sensorService;

    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator, SensorService sensorService) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.sensorService = sensorService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {

        this.measurementValidator.validate(measurementDTO, bindingResult);

        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for(FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new IncorrectMeasurementException(errorMessage.toString());
        }

        // тут он присвоит только value и raining, а Sensor уже надо сделать самому
        Measurement measurement = MeasurementMapper.convertDtoToModel(measurementDTO);
        this.measurementService.save(measurement, measurementDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(IncorrectMeasurementException e){
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    public MeasurementResponse getAllMeasurements(@RequestParam(value = "sensorName", required = false) String sensorName){
        List<MeasurementDTO> result = new ArrayList<>();
        List<Measurement> measurements = new ArrayList<>();

        if(sensorName != null){
            Sensor sensor = this.sensorService.findByName(sensorName);
            if(sensor != null)
                measurements = this.measurementService.findAllBySensor(sensor);
        }
        else{
            measurements = this.measurementService.findAll();
        }

        for(Measurement measurement : measurements)
            result.add(MeasurementMapper.convertModelToDto(measurement));
        return new MeasurementResponse(result);
    }

    @GetMapping("/rainyDaysCount")
    public Integer getRainyDaysCount(@RequestParam(value = "sensorName", required = false) String sensorName){
        List<Measurement> measurements = new ArrayList<>();
        int countOfRainyDays = 0;

        if(sensorName != null){
            Sensor sensor = this.sensorService.findByName(sensorName);
            if(sensor != null)
                measurements = this.measurementService.findAllBySensor(sensor);
        }
        else{
            measurements = this.measurementService.findAll();
        }

        for(Measurement measurement : measurements)
            if(measurement.getRaining())
                countOfRainyDays++;

        return countOfRainyDays;
    }


}

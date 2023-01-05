package org.suai.restApp.util.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.suai.restApp.dto.SensorDTO;
import org.suai.restApp.models.Sensor;
import org.suai.restApp.services.interfaces.SensorService;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;

        if(this.sensorService.findByName(sensorDTO.getName()) != null)
            errors.rejectValue("name", "", "Сенсор с таким именем уже существует!");

    }

}

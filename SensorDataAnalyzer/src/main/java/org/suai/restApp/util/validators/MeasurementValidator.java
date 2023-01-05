package org.suai.restApp.util.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.suai.restApp.dto.MeasurementDTO;
import org.suai.restApp.dto.SensorDTO;
import org.suai.restApp.services.interfaces.MeasurementService;
import org.suai.restApp.services.interfaces.SensorService;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;

        SensorDTO sensorDTO = measurementDTO.getSensor();
        // если в json не пришел сенсор, то это мы уже обработаем аннотацией @NotNull
        if(sensorDTO != null) {
            if (this.sensorService.findByName(measurementDTO.getSensor().getName()) == null)
                errors.rejectValue("sensor", "", "Сенсора с таким именем не существует!");
        }

    }

}

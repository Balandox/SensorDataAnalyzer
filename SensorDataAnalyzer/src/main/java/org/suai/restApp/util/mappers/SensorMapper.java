package org.suai.restApp.util.mappers;

import org.modelmapper.ModelMapper;
import org.suai.restApp.dto.SensorDTO;
import org.suai.restApp.models.Sensor;

public class SensorMapper {

    public static Sensor convertDtoToModel(SensorDTO sensorDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    public static SensorDTO convertModelToDto(Sensor sensor){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(sensor, SensorDTO.class);
    }

}

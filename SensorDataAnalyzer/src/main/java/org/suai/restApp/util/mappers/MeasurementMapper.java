package org.suai.restApp.util.mappers;

import org.modelmapper.ModelMapper;
import org.suai.restApp.dto.MeasurementDTO;
import org.suai.restApp.models.Measurement;
import org.suai.restApp.models.Sensor;

import java.time.LocalDateTime;

public class MeasurementMapper {

    public static Measurement convertDtoToModel(MeasurementDTO measurementDTO){
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public static MeasurementDTO convertModelToDto(Measurement measurement){
        ModelMapper modelMapper = new ModelMapper();

        MeasurementDTO measurementDTO= modelMapper.map(measurement, MeasurementDTO.class);
        measurementDTO.setSensor(SensorMapper.convertModelToDto(measurement.getSensor()));
        return measurementDTO;
    }

}

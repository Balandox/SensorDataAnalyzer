package org.suai.restApp.services.interfaces;

import org.suai.restApp.dto.MeasurementDTO;
import org.suai.restApp.models.Measurement;
import org.suai.restApp.models.Sensor;

import java.util.List;

public interface MeasurementService {
    void save(Measurement measurement, MeasurementDTO measurementDTO);

    List<Measurement> findAll();

    List<Measurement> findAllBySensor(Sensor sensor);

}

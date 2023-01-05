package org.suai.restApp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.suai.restApp.dto.MeasurementDTO;
import org.suai.restApp.models.Measurement;
import org.suai.restApp.models.Sensor;
import org.suai.restApp.repositories.MeasurementRepository;
import org.suai.restApp.services.interfaces.MeasurementService;
import org.suai.restApp.services.interfaces.SensorService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;

    private final SensorService sensorService;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void save(Measurement measurement, MeasurementDTO measurementDTO){
        measurement.setSensor(this.sensorService.findByName(measurementDTO.getSensor().getName()));
        measurement.setTime(LocalDateTime.now());

        this.measurementRepository.save(measurement);
    }

    public List<Measurement> findAll(){
        return this.measurementRepository.findAll();
    }

    public List<Measurement> findAllBySensor(Sensor sensor){
        return this.measurementRepository.findAllBySensor(sensor);
    }

}

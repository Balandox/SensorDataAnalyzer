package org.suai.restApp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.suai.restApp.exceptions.SensorNotFoundException;
import org.suai.restApp.models.Sensor;
import org.suai.restApp.repositories.SensorRepository;
import org.suai.restApp.services.interfaces.SensorService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorServiceImpl implements SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll(){
        return this.sensorRepository.findAll();
    }

    public Sensor findById(int id){
        Optional<Sensor> sensor = this.sensorRepository.findById(id);
        return sensor.orElseThrow(SensorNotFoundException::new);
    }

    public Sensor findByName(String name){

        return this.sensorRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor){
        this.sensorRepository.save(sensor);
    }
}

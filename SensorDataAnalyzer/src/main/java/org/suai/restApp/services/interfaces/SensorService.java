package org.suai.restApp.services.interfaces;

import org.springframework.stereotype.Service;
import org.suai.restApp.models.Sensor;

import java.util.List;


public interface SensorService {

    List<Sensor> findAll();

    Sensor findByName(String name);

    Sensor findById(int id);

    void save(Sensor sensor);

}

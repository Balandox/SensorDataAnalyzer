package org.suai.restApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.suai.restApp.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    Sensor findByName(String name);

}

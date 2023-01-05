package org.suai.restApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.suai.restApp.models.Measurement;
import org.suai.restApp.models.Sensor;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    List<Measurement> findAllBySensor(Sensor sensor);

}

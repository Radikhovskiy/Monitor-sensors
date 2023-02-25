package com.monitor.sensors.repository;

import com.monitor.sensors.model.Sensor;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findAllByNameContains(String namePart, PageRequest pageRequest);

    Long countByNameContains(String name);
}

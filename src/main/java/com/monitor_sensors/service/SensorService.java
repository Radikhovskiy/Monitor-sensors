package com.monitor_sensors.service;

import com.monitor_sensors.model.Sensor;
import java.util.List;

public interface SensorService {
    Sensor save(Sensor sensor);

    Sensor update(Long id, Sensor sensor);

    Sensor findById(Long id);

    List<Sensor> findAll();

    void deleteById(Long id);
}

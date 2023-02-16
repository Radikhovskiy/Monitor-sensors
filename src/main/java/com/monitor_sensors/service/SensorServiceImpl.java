package com.monitor_sensors.service;

import com.monitor_sensors.model.Sensor;
import com.monitor_sensors.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {
    private final SensorRepository repository;

    @Override
    public Sensor save(Sensor sensor) {
        return repository.save(sensor);
    }

    @Override
    public Sensor update(Long id, Sensor sensor) {
        sensor.setId(id);
        return save(sensor);
    }

    @Override
    public Sensor findById(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Sensor> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

package com.monitor.sensors.service;

import com.monitor.sensors.dto.SensorRespDto;
import com.monitor.sensors.model.Sensor;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

public interface SensorService {
    Sensor save(Sensor sensor);

    Sensor update(Long id, Sensor sensor);

    Sensor findById(Long id);

    List<Sensor> findAll();

    List<Sensor> findAll(PageRequest pageRequest);

    void deleteById(Long id);

    List<Sensor> findAllByNameContains(String namePart, PageRequest pageRequest);

    Long count();

    Long countByNameContains(String name);

    void setModelIfAdmin(Authentication authentication, Model model);

    void setSensorsAndPaginationToModel(
            List<SensorRespDto> sensorsDto, PageRequest pageRequest, Model model);

    void setSensorsAndPaginationToModelIfNameContains(
            String name, List<SensorRespDto> sensorsDto, PageRequest pageRequest, Model model);
}

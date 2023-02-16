package com.monitor_sensors.mapper;

import com.monitor_sensors.dto.SensorReqDto;
import com.monitor_sensors.dto.SensorRespDto;
import com.monitor_sensors.model.Sensor;
import org.springframework.stereotype.Component;

@Component
public class SensorMapper {
    public Sensor toModel(SensorReqDto dto) {
        Sensor sensor = new Sensor();
        sensor.setName(dto.getName());
        return sensor;
    }

    public SensorRespDto toDto(Sensor sensor) {
        SensorRespDto dto = new SensorRespDto();
        dto.setId(sensor.getId());
        dto.setName(sensor.getName());
        return dto;
    }
}

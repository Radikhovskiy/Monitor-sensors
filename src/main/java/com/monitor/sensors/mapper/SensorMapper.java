package com.monitor.sensors.mapper;

import com.monitor.sensors.dto.SensorReqDto;
import com.monitor.sensors.dto.SensorRespDto;
import com.monitor.sensors.model.Sensor;
import org.springframework.stereotype.Component;

@Component
public class SensorMapper {
    public Sensor toModel(SensorReqDto dto) {
        Sensor sensor = new Sensor();
        sensor.setName(dto.getName());
        sensor.setModel(dto.getModel());
        sensor.setLocation(dto.getLocation());
        sensor.setRangeFrom(dto.getRangeFrom());
        sensor.setRangeTo(dto.getRangeTo());
        sensor.setType(dto.getType());
        sensor.setUnit(dto.getUnit());
        sensor.setDescription(dto.getDescription());
        return sensor;
    }

    public SensorRespDto toDto(Sensor sensor) {
        SensorRespDto dto = new SensorRespDto();
        dto.setId(sensor.getId());
        dto.setName(sensor.getName());
        dto.setModel(sensor.getModel());
        dto.setLocation(sensor.getLocation());
        dto.setRangeFrom(sensor.getRangeFrom());
        dto.setRangeTo(sensor.getRangeTo());
        dto.setType(sensor.getType());
        dto.setUnit(sensor.getUnit());
        dto.setDescription(sensor.getDescription());
        return dto;
    }
}

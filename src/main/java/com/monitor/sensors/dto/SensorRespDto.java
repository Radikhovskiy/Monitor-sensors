package com.monitor.sensors.dto;

import com.monitor.sensors.model.Type;
import com.monitor.sensors.model.Unit;
import lombok.Data;

@Data
public class SensorRespDto {
    private Long id;

    private String name;

    private String model;

    private String location;

    private short rangeFrom;

    private short rangeTo;

    private Type type;

    private Unit unit;

    private String description;
}

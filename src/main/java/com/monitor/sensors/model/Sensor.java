package com.monitor.sensors.model;

import com.monitor.sensors.util.UnitConvertor;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sensors")
@Data
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String model;

    private String location;

    private short rangeFrom;

    private short rangeTo;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Convert(converter = UnitConvertor.class)
    private Unit unit;

    private String description;
}

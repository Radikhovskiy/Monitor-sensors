package com.monitor_sensors.model;

import com.monitor_sensors.util.SymbolConvertor;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sensors")
@Data
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private String name;

    private String model;

    private String location;

    private byte rangeFrom;

    private byte rangeTo;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Convert(converter = SymbolConvertor.class)
    private Unit unit;

    private String description;
}

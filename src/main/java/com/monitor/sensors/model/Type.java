package com.monitor.sensors.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Type {
    PRESSURE("Pressure"),
    VOLTAGE("Voltage"),
    TEMPERATURE("Temperature"),
    HUMIDITY("Humidity");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<String> getValues() {
        return Arrays.stream(Type.values()).map(Type::getValue).collect(Collectors.toList());
    }
}

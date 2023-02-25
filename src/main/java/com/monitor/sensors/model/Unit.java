package com.monitor.sensors.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Unit {
    BAR("bar"),
    VOLTAGE("voltage"),
    CELSIUS("°С"),
    PERCENT("%");

    private final String value;

    Unit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<String> getValues() {
        return Arrays.stream(Unit.values()).map(Unit::getValue).collect(Collectors.toList());
    }
}

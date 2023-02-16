package com.monitor_sensors.model;

public enum Unit {
    BAR("bar"),
    VOLTAGE("voltage"),
    CELSIUS("°С"),
    PERCENT("%");

    private final String symbol;

    Unit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

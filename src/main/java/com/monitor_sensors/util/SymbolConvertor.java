package com.monitor_sensors.util;

import com.monitor_sensors.model.Unit;
import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class SymbolConvertor implements AttributeConverter<Unit, String> {
    @Override
    public String convertToDatabaseColumn(Unit unit) {
        return unit == null ? null : Arrays.stream(Unit.values())
                .filter(u -> u.equals(unit))
                .map(Unit::getSymbol)
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Unit convertToEntityAttribute(String symbol) {
        return symbol == null ? null : Arrays.stream(Unit.values())
                .filter(v -> (v.getSymbol()).equals(symbol))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }
}

package com.monitor.sensors.util;

import com.monitor.sensors.model.Unit;
import java.util.Arrays;
import java.util.NoSuchElementException;
import javax.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

@Component
public class UnitConvertor implements AttributeConverter<Unit, String> {
    @Override
    public String convertToDatabaseColumn(Unit unit) {
        return unit == null ? null : Arrays.stream(Unit.values())
                .filter(u -> u.equals(unit))
                .map(Unit::getValue)
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Unit convertToEntityAttribute(String value) {
        return value == null ? null : Arrays.stream(Unit.values())
                .filter(v -> (v.getValue()).equals(value))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }
}

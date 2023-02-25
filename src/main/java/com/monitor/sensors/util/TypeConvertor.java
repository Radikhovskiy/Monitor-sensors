package com.monitor.sensors.util;

import com.monitor.sensors.model.Type;
import java.util.Arrays;
import java.util.NoSuchElementException;
import javax.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

@Component
public class TypeConvertor implements AttributeConverter<Type, String> {
    @Override
    public String convertToDatabaseColumn(Type type) {
        return type == null ? null : Arrays.stream(Type.values())
                .filter(t -> t.equals(type))
                .map(Type::getValue)
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Type convertToEntityAttribute(String value) {
        return value == null ? null : Arrays.stream(Type.values())
                .filter(v -> (v.getValue()).equals(value))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }
}

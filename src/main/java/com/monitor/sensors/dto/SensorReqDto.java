package com.monitor.sensors.dto;

import com.monitor.sensors.model.Type;
import com.monitor.sensors.model.Unit;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class SensorReqDto {
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Model should not be empty")
    @Size(min = 2, max = 15, message = "Model should be between 2 and 15 characters")
    private String model;

    @Size(max = 40, message = "Location should be no more than 40 characters")
    private String location;

    @Max(value = 200, message = "Range from should be less than 200")
    @Digits(integer = 3, fraction = 0, message = "Range from should be a whole number")
    private short rangeFrom;
    @Max(value = 200, message = "Range from should be less than 200")
    @Digits(integer = 3, fraction = 0, message = "Range from should be a whole number")
    private short rangeTo;

    private Type type;

    private Unit unit;

    @Size(max = 200, message = "Location should be no more than 200 characters")
    private String description;

    @AssertTrue(message = "Range from must be less than Range to")
    private boolean isRangeValid() {
        return rangeFrom <= rangeTo;
    }
}

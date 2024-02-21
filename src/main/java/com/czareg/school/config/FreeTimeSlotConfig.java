package com.czareg.school.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties(prefix = "free.time.slot")
@Getter
public class FreeTimeSlotConfig {

    @Min(0) @Max(23)
    int startHour;
    @Min(0) @Max(59)
    int startMinute;
    @Min(0) @Max(23)
    int endHour;
    @Min(0) @Max(59)
    int endMinute;
}
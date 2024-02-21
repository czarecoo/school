package com.czareg.school.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "free.time.slot")
@Getter
@RequiredArgsConstructor
public class FreeTimeSlotConfig {

    @Min(0)
    @Max(23)
    private final int startHour;
    @Min(0)
    @Max(59)
    private final int startMinute;
    @Min(0)
    @Max(23)
    private final int endHour;
    @Min(0)
    @Max(59)
    private final int endMinute;
}
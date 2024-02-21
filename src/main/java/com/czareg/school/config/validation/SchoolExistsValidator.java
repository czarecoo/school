package com.czareg.school.config.validation;

import com.czareg.school.feature.school.SchoolService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolExistsValidator implements ConstraintValidator<SchoolExists, Long> {

    private final SchoolService schoolService;

    @Override
    public boolean isValid(Long schoolId, ConstraintValidatorContext cxt) {
        if (schoolId == null || schoolId < 0) {
            return false;
        }
        return schoolService.existsById(schoolId);
    }
}
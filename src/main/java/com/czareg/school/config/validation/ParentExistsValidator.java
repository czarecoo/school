package com.czareg.school.config.validation;

import com.czareg.school.feature.parent.ParentService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParentExistsValidator implements ConstraintValidator<ParentExists, Long> {

    private final ParentService parentService;

    @Override
    public boolean isValid(Long parentId, ConstraintValidatorContext cxt) {
        if (parentId == null || parentId < 0) {
            return false;
        }
        return parentService.existsById(parentId);
    }
}
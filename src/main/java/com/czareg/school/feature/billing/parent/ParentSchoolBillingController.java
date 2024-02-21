package com.czareg.school.feature.billing.parent;

import com.czareg.school.feature.billing.parent.dto.ParentSchoolBillingRequestDTO;
import com.czareg.school.feature.billing.parent.dto.ParentSchoolBillingResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class ParentSchoolBillingController {

    private final ParentSchoolBillingService parentSchoolBillingService;

    @PostMapping("/billing/parent/school")
    public ParentSchoolBillingResponseDTO calculate(@Valid @RequestBody @NonNull ParentSchoolBillingRequestDTO parentSchoolBillingRequestDTO) {
        return parentSchoolBillingService.calculate(parentSchoolBillingRequestDTO);
    }
}

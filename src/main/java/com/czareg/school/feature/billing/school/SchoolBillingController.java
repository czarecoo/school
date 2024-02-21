package com.czareg.school.feature.billing.school;

import com.czareg.school.feature.billing.school.dto.SchoolBillingRequestDTO;
import com.czareg.school.feature.billing.school.dto.SchoolBillingResponseDTO;
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
public class SchoolBillingController {

    private final SchoolBillingService schoolBillingService;

    @PostMapping("/billing/school")
    public SchoolBillingResponseDTO calculate(@Valid @RequestBody @NonNull SchoolBillingRequestDTO schoolBillingRequestDTO) {
        return schoolBillingService.calculate(schoolBillingRequestDTO);
    }
}

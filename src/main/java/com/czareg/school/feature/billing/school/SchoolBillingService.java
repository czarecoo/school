package com.czareg.school.feature.billing.school;

import com.czareg.school.feature.billing.common.component.ChildProcessor;
import com.czareg.school.feature.billing.common.component.SchoolBillingPreparer;
import com.czareg.school.feature.billing.common.dto.ChildDTO;
import com.czareg.school.feature.billing.common.dto.TotalBillingDTO;
import com.czareg.school.feature.billing.school.dto.SchoolBillingRequestDTO;
import com.czareg.school.feature.billing.school.dto.SchoolBillingResponseDTO;
import com.czareg.school.feature.child.Child;
import com.czareg.school.feature.child.ChildService;
import com.czareg.school.feature.school.School;
import com.czareg.school.feature.school.SchoolService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class SchoolBillingService {

    private final ChildService childService;
    private final SchoolService schoolService;
    private final ChildProcessor childProcessor;
    private final SchoolBillingPreparer schoolBillingPreparer;

    public SchoolBillingResponseDTO calculate(@NonNull SchoolBillingRequestDTO schoolBillingRequestDTO) {
        long schoolId = schoolBillingRequestDTO.schoolId();
        School school = schoolService.findOrThrow(schoolId);
        int month = schoolBillingRequestDTO.month();
        List<Child> childList = childService.findBySchoolId(schoolId);
        List<ChildDTO> childDTOList = childProcessor.process(childList, school, month);
        TotalBillingDTO totalBillingDTO = schoolBillingPreparer.prepare(childDTOList, school);
        return new SchoolBillingResponseDTO(totalBillingDTO, childDTOList);
    }
}

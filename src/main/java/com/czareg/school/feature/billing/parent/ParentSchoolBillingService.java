package com.czareg.school.feature.billing.parent;

import com.czareg.school.feature.billing.common.component.ChildProcessor;
import com.czareg.school.feature.billing.common.component.SchoolBillingPreparer;
import com.czareg.school.feature.billing.common.dto.ChildDTO;
import com.czareg.school.feature.billing.common.dto.TotalBillingDTO;
import com.czareg.school.feature.billing.parent.dto.ParentSchoolBillingRequestDTO;
import com.czareg.school.feature.billing.parent.dto.ParentSchoolBillingResponseDTO;
import com.czareg.school.feature.child.Child;
import com.czareg.school.feature.child.ChildService;
import com.czareg.school.feature.school.School;
import com.czareg.school.feature.school.SchoolService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentSchoolBillingService {

    private final SchoolService schoolService;
    private final ChildService childService;
    private final ChildProcessor childProcessor;
    private final SchoolBillingPreparer schoolBillingPreparer;

    public ParentSchoolBillingResponseDTO calculate(@NonNull ParentSchoolBillingRequestDTO parentSchoolBillingRequestDTO) {
        long schoolId = parentSchoolBillingRequestDTO.schoolId();
        School school = schoolService.findOrThrow(schoolId);
        long parentId = parentSchoolBillingRequestDTO.parentId();
        int month = parentSchoolBillingRequestDTO.month();
        List<Child> childList = childService.findBySchoolIdAndParentId(schoolId, parentId);
        List<ChildDTO> childDTOList = childProcessor.process(childList, school, month);
        TotalBillingDTO totalBillingDTO = schoolBillingPreparer.prepare(childDTOList, school);
        return new ParentSchoolBillingResponseDTO(totalBillingDTO, childDTOList);
    }
}

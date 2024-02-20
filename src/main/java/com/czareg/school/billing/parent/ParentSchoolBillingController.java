package com.czareg.school.billing.parent;

import com.czareg.school.billing.common.dto.ChildBillingDTO;
import com.czareg.school.billing.common.dto.TotalBillingDTO;
import com.czareg.school.billing.parent.dto.ChildDTO;
import com.czareg.school.billing.parent.dto.ParentSchoolBillingRequestDTO;
import com.czareg.school.billing.parent.dto.ParentSchoolBillingResponseDTO;
import com.czareg.school.billing.school.component.ChildBillingPreparer;
import com.czareg.school.billing.school.component.SchoolBillingPreparer;
import com.czareg.school.child.Child;
import com.czareg.school.child.ChildRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class ParentSchoolBillingController {

    private final ChildRepository childRepository;
    private final SchoolBillingPreparer schoolBillingPreparer;
    private final ChildBillingPreparer childBillingPreparer;

    @PostMapping("/billing/parent/school")
    public ParentSchoolBillingResponseDTO calculate(@Valid @RequestBody @NonNull ParentSchoolBillingRequestDTO parentSchoolBillingRequestDTO) {
        long schoolId = parentSchoolBillingRequestDTO.schoolId();
        long parentId = parentSchoolBillingRequestDTO.parentId();
        int month = parentSchoolBillingRequestDTO.month();
        List<Child> childList = childRepository.findBySchoolIdAndParentId(schoolId, parentId);
        List<ChildDTO> childDTOList = new ArrayList<>();
        for (Child child : childList) {
            ChildBillingDTO childBillingDTO = childBillingPreparer.prepare(child, month);
            ChildDTO childDTO = new ChildDTO(child.getId(), child.getFirstname(), child.getLastname(), childBillingDTO);
            childDTOList.add(childDTO);
        }
        TotalBillingDTO totalBillingDTO = schoolBillingPreparer.prepare(childDTOList.stream().map(ChildDTO::billing).toList());
        return new ParentSchoolBillingResponseDTO(totalBillingDTO, childDTOList);
    }
}

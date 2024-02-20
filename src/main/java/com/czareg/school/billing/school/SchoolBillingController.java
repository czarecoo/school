package com.czareg.school.billing.school;

import com.czareg.school.billing.common.component.ChildBillingPreparer;
import com.czareg.school.billing.common.component.SchoolBillingPreparer;
import com.czareg.school.billing.common.dto.ChildBillingDTO;
import com.czareg.school.billing.common.dto.TotalBillingDTO;
import com.czareg.school.billing.school.dto.ChildDTO;
import com.czareg.school.billing.school.dto.ParentDTO;
import com.czareg.school.billing.school.dto.SchoolBillingRequestDTO;
import com.czareg.school.billing.school.dto.SchoolBillingResponseDTO;
import com.czareg.school.child.Child;
import com.czareg.school.child.ChildRepository;
import com.czareg.school.parent.Parent;
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
public class SchoolBillingController {

    private final ChildRepository childRepository;
    private final SchoolBillingPreparer schoolBillingPreparer;
    private final ChildBillingPreparer childBillingPreparer;

    @PostMapping("/billing/school")
    public SchoolBillingResponseDTO calculate(@Valid @RequestBody @NonNull SchoolBillingRequestDTO schoolBillingRequestDTO) {
        Long schoolId = schoolBillingRequestDTO.schoolId();
        int month = schoolBillingRequestDTO.month();
        List<Child> childList = childRepository.findBySchoolId(schoolId);
        List<ChildDTO> childDTOList = new ArrayList<>();
        for (Child child : childList) {
            Parent parent = child.getParent();
            ParentDTO parentDTO = new ParentDTO(parent.getId(), parent.getFirstname(), parent.getLastname());
            ChildBillingDTO childBillingDTO = childBillingPreparer.prepare(child, month);
            ChildDTO childDTO = new ChildDTO(child.getId(), child.getFirstname(), child.getLastname(), parentDTO, childBillingDTO);
            childDTOList.add(childDTO);
        }
        TotalBillingDTO totalBillingDTO = schoolBillingPreparer.prepare(childDTOList.stream().map(ChildDTO::billing).toList());
        return new SchoolBillingResponseDTO(totalBillingDTO, childDTOList);
    }
}

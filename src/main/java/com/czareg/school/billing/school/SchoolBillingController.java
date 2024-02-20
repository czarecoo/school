package com.czareg.school.billing.school;

import com.czareg.school.billing.school.components.ChildBillingPreparer;
import com.czareg.school.billing.school.components.SchoolBillingPreparer;
import com.czareg.school.billing.school.dto.*;
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
    public SchoolBillingResponseDTO calculateSchoolBilling(@Valid @RequestBody @NonNull SchoolBillingRequestDTO schoolBillingRequestDTO) {
        Long schoolId = schoolBillingRequestDTO.schoolId();
        int month = schoolBillingRequestDTO.month();
        List<Child> children = childRepository.findBySchoolId(schoolId);
        List<ChildDTO> childBillings = new ArrayList<>();
        for (Child child : children) {
            Parent parent = child.getParent();
            ParentDTO parentDTO = new ParentDTO(parent.getId(), parent.getFirstname(), parent.getLastname());
            ChildBillingDTO childBilling = childBillingPreparer.prepare(child, month);
            ChildDTO childDTO = new ChildDTO(child.getId(), child.getFirstname(), child.getLastname(), parentDTO, childBilling);
            childBillings.add(childDTO);
        }
        SchoolBillingDTO schoolBilling = schoolBillingPreparer.prepare(childBillings);
        return new SchoolBillingResponseDTO(schoolBilling, childBillings);
    }
}

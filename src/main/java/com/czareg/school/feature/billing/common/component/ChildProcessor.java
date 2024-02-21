package com.czareg.school.feature.billing.common.component;

import com.czareg.school.feature.attendence.Attendance;
import com.czareg.school.feature.attendence.AttendenceService;
import com.czareg.school.feature.billing.common.dto.ChildBillingDTO;
import com.czareg.school.feature.billing.common.dto.ChildDTO;
import com.czareg.school.feature.billing.common.dto.ParentDTO;
import com.czareg.school.feature.child.Child;
import com.czareg.school.feature.parent.Parent;
import com.czareg.school.feature.school.School;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChildProcessor {

    private final AttendenceService attendenceService;
    private final ChildBillingPreparer childBillingPreparer;

    public List<ChildDTO> process(List<Child> childList, School school, int month) {
        List<ChildDTO> childDTOList = new ArrayList<>();
        for (Child child : childList) {
            List<Attendance> attendanceList = attendenceService.findByChildIdAndMonth(child.getId(), month);
            ChildBillingDTO childBillingDTO = childBillingPreparer.prepare(attendanceList, school);
            Parent parent = child.getParent();
            ParentDTO parentDTO = new ParentDTO(parent.getId(), parent.getFirstname(), parent.getLastname());
            ChildDTO childDTO = new ChildDTO(child.getId(), child.getFirstname(), child.getLastname(), parentDTO, childBillingDTO);
            childDTOList.add(childDTO);
        }
        return childDTOList;
    }
}

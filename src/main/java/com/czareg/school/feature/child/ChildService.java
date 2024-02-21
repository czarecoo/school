package com.czareg.school.feature.child;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;

    public List<Child> findBySchoolId(long schoolId) {
        return childRepository.findBySchoolId(schoolId);
    }

    public List<Child> findBySchoolIdAndParentId(long schoolId, long parentId) {
        return childRepository.findBySchoolIdAndParentId(schoolId, parentId);
    }
}

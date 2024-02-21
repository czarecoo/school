package com.czareg.school.feature.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;

    public boolean existsById(long parentId) {
        return parentRepository.existsById(parentId);
    }
}

package com.czareg.school.feature.child;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ChildRepositoryTest {

    @Autowired
    ChildRepository childRepository;

    @Test
    void shouldReturnEmptyListOfAttendanceForNotExistingSchool() {
        List<Child> childList = childRepository.findBySchoolId(999L);

        assertTrue(childList.isEmpty());
    }

    @Test
    void shouldReturnTwoChildrenForSchoolIdThree() {
        List<Child> childList = childRepository.findBySchoolId(3);

        assertEquals(2, childList.size());
    }

    @Test
    void shouldReturnEmptyListOfAttendanceForNotParent() {
        List<Child> childList = childRepository.findBySchoolIdAndParentId(1L, 999L);

        assertTrue(childList.isEmpty());
    }

    @Test
    void shouldReturnOneChildForSchoolIdThreeAndParentIdOne() {
        List<Child> childList = childRepository.findBySchoolIdAndParentId(3, 2);

        assertEquals(1, childList.size());
    }
}
package com.czareg.school.feature.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class SchoolRepositoryTest {

    @Autowired
    SchoolRepository schoolrepository;

    @Test
    void shouldFindThreeSchools() {
        List<School> schoolList = schoolrepository.findAll();

        assertEquals(3, schoolList.size());
    }

    @Test
    void shouldReturnTrueExistByIdForIdOne() {
        boolean exists = schoolrepository.existsById(1L);

        assertTrue(exists);
    }
}
package com.czareg.school.feature.school;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SchoolServiceTest {

    @Mock
    private SchoolRepository schoolRepository;

    @InjectMocks
    private SchoolService schoolService;

    @Test
    void shouldReturnSchoolWhenRepositoryFindsOptionalWithSchoolForGivenSchoolId() {
        long schoolId = 1L;
        School school = new School();
        when(schoolRepository.findById(schoolId)).thenReturn(Optional.of(school));

        School result = schoolService.findOrThrow(schoolId);

        assertEquals(school, result);
    }

    @Test
    void shouldThrowWhenRepositoryFindsEmptyOptionalForGivenSchoolId() {
        long schoolId = 1L;
        when(schoolRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> schoolService.findOrThrow(schoolId));
    }
}
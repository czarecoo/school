package com.czareg.school.feature.school;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SchoolRepository extends JpaRepository<School, Long> {
}

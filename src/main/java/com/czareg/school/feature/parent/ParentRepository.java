package com.czareg.school.feature.parent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ParentRepository extends JpaRepository<Parent, Long> {
}

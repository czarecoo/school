package com.czareg.school.feature.child;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ChildRepository extends JpaRepository<Child, Long> {

    List<Child> findBySchoolId(long schoolId);

    List<Child> findBySchoolIdAndParentId(long schoolId, long parentId);
}

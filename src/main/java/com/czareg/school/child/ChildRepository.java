package com.czareg.school.child;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

    List<Child> findBySchoolId(long schoolId);

    List<Child> findBySchoolIdAndParentId(long schoolId, long parentId);
}

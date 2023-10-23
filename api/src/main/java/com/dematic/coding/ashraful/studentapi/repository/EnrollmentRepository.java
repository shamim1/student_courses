package com.dematic.coding.ashraful.studentapi.repository;

import com.dematic.coding.ashraful.studentapi.model.Enrollment;
import com.dematic.coding.ashraful.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Query("SELECT e FROM Enrollment e WHERE e.student = :student")
    List<Enrollment> findByStudent(@Param("student") Student student);
}

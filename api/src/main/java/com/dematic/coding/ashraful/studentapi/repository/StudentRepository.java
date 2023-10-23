package com.dematic.coding.ashraful.studentapi.repository;

import com.dematic.coding.ashraful.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

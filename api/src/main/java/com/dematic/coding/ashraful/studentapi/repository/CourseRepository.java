package com.dematic.coding.ashraful.studentapi.repository;

import com.dematic.coding.ashraful.studentapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

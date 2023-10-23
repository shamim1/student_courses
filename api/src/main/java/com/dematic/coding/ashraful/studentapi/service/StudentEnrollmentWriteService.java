package com.dematic.coding.ashraful.studentapi.service;

import com.dematic.coding.ashraful.studentapi.model.Course;
import com.dematic.coding.ashraful.studentapi.model.Student;
import com.dematic.coding.ashraful.studentapi.repository.CourseRepository;
import com.dematic.coding.ashraful.studentapi.repository.EnrollmentRepository;
import com.dematic.coding.ashraful.studentapi.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Note: Any time we write to the DB, we should put the Service code here.
// We can read the data after we finish writing the data.
@Slf4j
@Service
public class StudentEnrollmentWriteService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    public Student saveAndGetStudent(Student student){
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            log.error("StudentEnrollmentWriteService::saveAndGetStudent error happened. Details: " + e.getMessage());
            return null;
        }
    }

    public Course saveAndGetCourse(Course course){
        try {
            return courseRepository.save(course);
        } catch (Exception e) {
            log.error("StudentEnrollmentWriteService::saveAndGetCourse error happened. Details: " + e.getMessage());
            return null;
        }
    }

}

package com.dematic.coding.ashraful.studentapi.service;

import com.dematic.coding.ashraful.studentapi.model.Course;
import com.dematic.coding.ashraful.studentapi.model.Enrollment;
import com.dematic.coding.ashraful.studentapi.model.Student;
import com.dematic.coding.ashraful.studentapi.repository.CourseRepository;
import com.dematic.coding.ashraful.studentapi.repository.EnrollmentRepository;
import com.dematic.coding.ashraful.studentapi.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// Note: This service is strictly to read data from the database.
@Slf4j
@Service
public class StudentEnrollmentReadService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    // Note: Implementing paging or any sort of restriction of feting data is considered as enhancement.
    public List<Student> getStudentList(){
        return studentRepository.findAll();
    }

    public List<Course> getCourseList(){
        return courseRepository.findAll();
    }

    public List<Enrollment> getEnrollmentList(){
        return enrollmentRepository.findAll();
    }

    public Optional<Student> findByStudentId(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public Optional<Course> findByCourseId(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public List<Enrollment> findEnrollmentByStudent(Student student) {
        return enrollmentRepository.findByStudent(student);
    }

    public boolean existsEnrollmentByCourseIdAndStudentId(long courseId, long studentId) {
        return enrollmentRepository.existsByCourseIdAndStudentId(courseId, studentId);
    }
}

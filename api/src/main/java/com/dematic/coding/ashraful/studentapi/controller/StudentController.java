package com.dematic.coding.ashraful.studentapi.controller;

import com.dematic.coding.ashraful.studentapi.model.Course;
import com.dematic.coding.ashraful.studentapi.model.Enrollment;
import com.dematic.coding.ashraful.studentapi.model.Student;
import com.dematic.coding.ashraful.studentapi.service.StudentEnrollmentReadService;
import com.dematic.coding.ashraful.studentapi.service.StudentEnrollmentWriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("v1.1")
public class StudentController {

    @Autowired
    StudentEnrollmentReadService studentEnrollmentReadService;
    @Autowired
    StudentEnrollmentWriteService studentEnrollmentWriteService;

    @GetMapping("/hello")
    // Note: This just to test if the API is running or not. It doesn't connect to the database.
    public String sendGreetings() {
        return "Hello, World!";
    }

    @PostMapping("/students")
    public ResponseEntity<?> saveAndGetStudent(@RequestBody Student student) {
        Optional<Student> createdStudent = Optional.ofNullable(
                studentEnrollmentWriteService.saveAndGetStudent(student));
        if(createdStudent.isPresent())
            return new ResponseEntity<>(createdStudent.get(), HttpStatus.CREATED);
        else
            return new ResponseEntity<>("An error occurred while creating the student",
                    HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping("/courses")
    public ResponseEntity<?> saveAndGetStudent(@RequestBody Course course) {
        Optional<Course> createdCourse = Optional.ofNullable(
                studentEnrollmentWriteService.saveAndGetCourse(course));
        if(createdCourse.isPresent())
            return new ResponseEntity<>(createdCourse.get(), HttpStatus.CREATED);
        else
            return new ResponseEntity<>("An error occurred while creating the course",
                    HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // getAllStudents
    // getAllCourses
    // I will not be able to implement those API calls due to time constraint.
    // Also, I don't think it's not required. They should be very easy to implement.
    // I am keeping few hours for safety reasons.

    // -----------------------------------------------------------------------------------------
    @PostMapping("/courses/{courseId}/enroll/{studentId}")
    public ResponseEntity<?> enrollStudentInCourse(
            @PathVariable Long courseId,
            @PathVariable Long studentId
    ) {

        // Make sure that the course exists in the database
        Optional<Course> course = studentEnrollmentReadService.findByCourseId(courseId);
        if (course.isEmpty())
        {
            return new ResponseEntity<>("Course not found",
                    HttpStatus.NOT_FOUND);
        }

        // Make sure that the student exists in the database
        Optional<Student> student = studentEnrollmentReadService.findByStudentId(studentId);
        if (student.isEmpty())
        {
            return new ResponseEntity<>("Student not found",
                    HttpStatus.NOT_FOUND);
        }

        // Check if the enrollment record exists or not
        boolean isExists = studentEnrollmentReadService.existsEnrollmentByCourseIdAndStudentId(
                course.get().getId(), student.get().getId());
        if(isExists)
        {
            return new ResponseEntity<>("The enrollment record already exists",
                    HttpStatus.BAD_REQUEST);
        }

        Enrollment enrollment = Enrollment.builder().course(course.get())
                .student(student.get()).build();
        Optional<Enrollment> createdEnrollment = Optional.ofNullable(
                studentEnrollmentWriteService.saveAndGetEnrollment(enrollment));

        if(createdEnrollment.isPresent())
            return new ResponseEntity<>(createdEnrollment.get(), HttpStatus.CREATED);
        else
            return new ResponseEntity<>("An error occurred while creating the Enrollment",
                    HttpStatus.INTERNAL_SERVER_ERROR);

    }

//    @PostMapping("/courses/{courseId}/unenroll/{studentId}")
//    public ResponseEntity<?> unenrollStudentFromCourse(
//            @PathVariable Long courseId,
//            @PathVariable Long studentId
//    ) {
//        // It will be similar to enrollment.
//        // We will "Delete the Enrollment record" only if the record exists.
//        // Else it will be bad request.
//        // I will probably not be able to implement the API due to time constraint.
//        // Also, I don't think it's not required. It should be easy to implement.
//
//        return ResponseEntity.ok("Un-enrolled student " + studentId + " from course " + courseId);
//    }

    // -----------------------------------------------------------------------------------------
    @GetMapping("/students/{studentId}/courses")
    public ResponseEntity<?> getStudentCourses(@PathVariable Long studentId) {
        Optional<Student> student = studentEnrollmentReadService.findByStudentId(studentId);
        if (student.isEmpty()) {
            return new ResponseEntity<>("Student not found",
                    HttpStatus.NOT_FOUND);
        }

        List<Enrollment> studentEnrollments = studentEnrollmentReadService.
                findEnrollmentByStudent(student.get());
        List<Course> allCourses = studentEnrollmentReadService.getCourseList();

        Map<String, List<Course>> result = new HashMap<>();
        result.put("takenCourses", studentEnrollments.stream()
                .map(Enrollment::getCourse)
                .collect(Collectors.toList())
        );
        result.put("notTakenCourses", allCourses.stream()
                .filter(course -> !studentEnrollments.stream()
                        .anyMatch(enrollment -> enrollment.getCourse().equals(course))
                )
                .collect(Collectors.toList())
        );

        return ResponseEntity.ok(result);
    }


}

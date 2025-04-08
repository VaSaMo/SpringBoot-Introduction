package com.example.introspring.integration;

import com.example.introspring.entity.Course;
import com.example.introspring.entity.Student;
import com.example.introspring.repository.CourseRepository;
import com.example.introspring.repository.EnrollmentRepository;
import com.example.introspring.repository.StudentRepository;
import com.example.introspring.service.CourseService;
import com.example.introspring.service.EnrollmentService;
import com.example.introspring.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EnrollmentServiceIntegrationTest {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;


    private Course course;
    private Student student;

    @BeforeEach
    void setup() {
        course = new Course();
        course.setName("Computación en Internet II");
        courseRepository.save(course);

        student = new Student();
        student.setName("Alice Andrew");
        studentRepository.save(student);
    }

    @Test
    void enrollStudent_WhenStudentAndCourseExist_ShouldSaveEnrollment(){
        // Arrange
        long studentId = student.getId();
        long courseId = course.getId();

        // Act
        enrollmentService.enrollStudentToCourse(studentId, courseId);

        // Assert
        var enrollment = enrollmentService.isStudentEnrolled(studentId, courseId);
        assertNotNull(enrollment);
    }

    @Test
    void enrollStudent_WhenStudentAlreadyEnrolled_ShouldNotDuplicateEnrollment(){
        enrollmentService.enrollStudentToCourse(student.getId(), course.getId());
        assertThrows(RuntimeException.class, () -> enrollmentService.enrollStudentToCourse(student.getId(), course.getId()));
    }

    @AfterEach
    void cleanup(){
        enrollmentRepository.deleteAll();
        studentRepository.deleteAll();
        courseRepository.deleteAll();
    }





}

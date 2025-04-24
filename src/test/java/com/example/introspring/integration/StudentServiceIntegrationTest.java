//package com.example.introspring.integration;
//
//import com.example.introspring.entity.Course;
//import com.example.introspring.entity.Student;
//import com.example.introspring.repository.CourseRepository;
//import com.example.introspring.repository.EnrollmentRepository;
//import com.example.introspring.repository.StudentRepository;
//import com.example.introspring.service.CourseService;
//import com.example.introspring.service.EnrollmentService;
//import com.example.introspring.service.StudentService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class StudentServiceIntegrationTest {
//
//    @Autowired
//    private CourseService courseService;
//
//    @Autowired
//    private StudentService studentService;
//
//    @Autowired
//    private EnrollmentService enrollmentService;
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    @Autowired
//    private EnrollmentRepository enrollmentRepository;
//
//    private List<Student> students;
//
//
//    @BeforeEach
//    void setup() {
//        Student student1 = new Student();
//        student1.setName("Alice Andrew");
//        student1 = studentService.createStudent(student1);
//
//        Student student2 = new Student();
//        student2.setName("Bob Brown");
//        student2 = studentService.createStudent(student2);
//
//        Student student3 = new Student();
//        student3.setName("Charlie Clark");
//        student3 = studentService.createStudent(student3);
//
//        students = List.of(student1, student2, student3);
//    }
//
//    @Test
//    void getEnrolledStudents_WhenCourseHasStudents_ShouldReturnStudentList(){
//        Course course = new Course();
//        course.setName("Computación en Internet II");
//        course = courseService.createCourse(course);
//
//        enrollmentService.enrollStudentToCourse(students.get(0).getId(), course.getId());
//        enrollmentService.enrollStudentToCourse(students.get(1).getId(), course.getId());
//        enrollmentService.enrollStudentToCourse(students.get(2).getId(), course.getId());
//
//        // Assert
//        assertEquals(3, studentService.listStudentsOfCourse(course.getId()).size());
//
//    }
//
//
//    @AfterEach
//    void cleanup() {
//        enrollmentRepository.deleteAll();
//        studentRepository.deleteAll();
//        courseRepository.deleteAll();
//    }
//
//
//}

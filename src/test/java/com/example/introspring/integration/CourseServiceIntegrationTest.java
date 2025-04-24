//package com.example.introspring.integration;
//import com.example.introspring.entity.Enrollment;
//import com.example.introspring.entity.Profesor;
//import com.example.introspring.entity.Course;
//import com.example.introspring.entity.Student;
//import com.example.introspring.repository.CourseRepository;
//import com.example.introspring.repository.EnrollmentRepository;
//import com.example.introspring.repository.ProfesorRepository;
//import com.example.introspring.repository.StudentRepository;
//import com.example.introspring.service.CourseService;
//import com.example.introspring.service.EnrollmentService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest //Carga el application context
//public class CourseServiceIntegrationTest {
//
//    @Autowired
//    private CourseService courseService;
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    @Autowired
//    private ProfesorRepository professorRepository;
//
//    @Autowired
//    private EnrollmentService enrollmentService;
//
//    @Autowired
//    private EnrollmentRepository enrollmentRepository;
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    private Profesor professor;
//    private Course course;
//
//    private Student student;
//
//    @BeforeEach //se ejecuta antes de cada test
//    void setup() {
//        professor = new Profesor();
//        professor.setName("Alice Andrew");
//        professor = professorRepository.save(professor);
//    }
//
//    void setUp1(){
//        student = new Student();
//        student.setName("Alice Andrew");
//        studentRepository.save(student);
//
//        course = new Course();
//        course.setName("Computación en Internet II");
//        course.setProfesor(professor);
//        courseRepository.save(course);
//
//        enrollmentService.enrollStudentToCourse(student.getId(), course.getId());
//    }
//
//    @Test
//    void createCourse_WhenValid_ReturnsSavedCourse() {
//        // Arrange
//        Course course = new Course();
//        course.setName("Computación en Internet II");
//        course.setProfesor(professor);
//
//        // Act
//        Course savedCourse = courseService.createCourse(course);
//
//        // Assert
//        assertNotNull(savedCourse.getId());
//        assertEquals("Computación en Internet II", savedCourse.getName());
//        assertNotNull(savedCourse.getProfesor());
//        assertEquals(professor.getId(), savedCourse.getProfesor().getId());
//
//        // Verificar que realmente está en la BD
//        Course foundCourse = courseRepository.findById(savedCourse.getId()).orElse(null);
//        assertNotNull(foundCourse);
//        assertEquals("Computación en Internet II", foundCourse.getName());
//    }
//
//    @Test
//    void saveCourse_WhenCourseAlreadyExists_ShouldThrowException() {
//        // Arrange
//        Course course = new Course();
//        course.setName("Computación en Internet II");
//        course.setProfesor(professor);
//        courseService.createCourse(course);
//
//        Course course2= new Course();
//        course2.setName("Computación en Internet II");
//        course2.setProfesor(professor);
//
//        // Act & Assert
//        assertThrows(RuntimeException.class, () -> courseService.createCourse(course2));
//    }
//
//    @Test
//    void deleteCourse_WhenCourseHasEnrollments_ShouldCascadeDeleteEnrollments(){
//        //Verificar que el curso exista
//        setUp1();
//        assertEquals(this.course.getId(),courseService.getCourseById(course.getId()).getId());
//        List<Enrollment> enrollments=enrollmentService.getEnrollmentsByCourse_Id(course.getId());
//        assertNotNull(enrollments);
//
//        //Act
//        courseService.deleteCourse(course.getId());
//
//        //Verificar que se elimino el enrollment
//        enrollments=enrollmentService.getEnrollmentsByCourse_Id(course.getId());
//        assertEquals(0, enrollments.size());
//    }
//
//
//    @AfterEach //se ejecuta después de cada test
//    void cleanup() {
//        courseRepository.deleteAll();
//        professorRepository.deleteAll();
//    }
//
//}
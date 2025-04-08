package com.example.introspring.service;

import java.util.List;
import com.example.introspring.entity.Course;
import com.example.introspring.entity.Profesor;

public interface CourseService {


    Course createCourse(Course course);


    List<Course> getAllCourses();

    List<Course> listCoursesOfStudent(long studentId);

    List<Course> findByProfesor(long profesorId);

    Course getCourseById(long l);


    void deleteCourse(long courseId);

}

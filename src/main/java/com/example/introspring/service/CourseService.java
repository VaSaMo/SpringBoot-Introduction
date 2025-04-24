package com.example.introspring.service;

import java.util.List;

import com.example.introspring.dto.CourseDTO;
import com.example.introspring.dto.CourseWithCountDTO;
import com.example.introspring.entity.Course;
import com.example.introspring.entity.Profesor;
import org.springframework.data.domain.Page;

public interface CourseService {


    CourseDTO createCourse(CourseDTO courseDTO);
    List<CourseDTO> getAllCourses();
    List<CourseDTO> listCoursesOfStudent(long studentId);
    List<CourseDTO> findByProfesor(long profesorId);

    CourseDTO getCourseById(long l);

    Page<CourseDTO> searchCoursesByName(String name, int page);


    void deleteCourse(long courseId);

    Page<CourseWithCountDTO> coursesWithStudentCount(int page);

}

package com.example.introspring.repository;

import com.example.introspring.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByProfesor_Id(long profesorId);

    Course getCourseById(long id);

    List<Course> findAll();

    Course findCourseById(long id);

    boolean existsByName(String name);

    Page<Course> findByNameContainingIgnoreCase(String name, Pageable pageable);
}


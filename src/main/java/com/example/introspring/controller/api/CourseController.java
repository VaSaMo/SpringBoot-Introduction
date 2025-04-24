package com.example.introspring.controller.api;

import com.example.introspring.dto.CourseDTO;
import com.example.introspring.dto.CourseWithCountDTO;
import com.example.introspring.dto.StudentDTO;
import com.example.introspring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll(){
        var courseList = courseService.getAllCourses();
        return ResponseEntity.status(200).body(courseList);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CourseDTO>> getCoursesOfStudent(@PathVariable long studentId){
        var courseList = courseService.listCoursesOfStudent(studentId);
        return ResponseEntity.status(200).body(courseList);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody CourseDTO course){
        var courseCreated = courseService.createCourse(course);
        return ResponseEntity.status(201).body(courseCreated);
    }

    @GetMapping("/search/{name}/{page}")
    public ResponseEntity<Page<CourseDTO>> find(@PathVariable String name, @PathVariable int page){
        var courseList = courseService.searchCoursesByName(name, page);
        return ResponseEntity.status(200).body(courseList);
    }

    @GetMapping("/studentCount/{page}")
    public ResponseEntity<Page<CourseWithCountDTO>> coursesWithStudentCount(@PathVariable int page){
        var courseList = courseService.coursesWithStudentCount(page);
        return ResponseEntity.status(200).body(courseList);
    }


//    @GetMapping
//    public ResponseEntity<?> all(){
//        return ResponseEntity.status(200).body(List.of("Curso1", "Curso2", "Curso3"));
//    }

}

package com.example.introspring.controller.api;

import com.example.introspring.dto.CourseDTO;
import com.example.introspring.dto.StudentDTO;
import com.example.introspring.service.CourseService;
import com.example.introspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<List<StudentDTO>> studentCourses(@PathVariable long courseId){
        var students = studentService.listStudentsOfCourse(courseId);
        return ResponseEntity.status(200).body(students);
    }

    @GetMapping("/{page}")
    public ResponseEntity<Page<StudentDTO>> findAll(@PathVariable int page){
        var courseList = studentService.findAll(page);
        return ResponseEntity.status(200).body(courseList);
    }

    @PostMapping()
    public ResponseEntity<?> registerStudent(@RequestBody StudentDTO student){
        var studentCreated = studentService.createStudent(student);
        return ResponseEntity.status(201).body(studentCreated);
    }

    @PostMapping("/update/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable long studentId, @RequestBody StudentDTO student){
        var studentUpdated = studentService.updateStudent(studentId, student);
        return ResponseEntity.status(200).body(studentUpdated);
    }

    @GetMapping("/program/{program}")
    public ResponseEntity<List<StudentDTO>> getByProgram(@PathVariable String program){
        var students = studentService.getByProgram(program);
        return ResponseEntity.status(200).body(students);
    }


}

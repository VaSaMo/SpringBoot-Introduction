package com.example.introspring.controller.api;

import com.example.introspring.dto.EnrollmentDTO;
import com.example.introspring.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity<EnrollmentDTO> enrollStudentToCourse(@PathVariable long studentId, @PathVariable long courseId){
        var enrolled = enrollmentService.enrollStudentToCourse(studentId, courseId);
        return ResponseEntity.status(200).body(enrolled);
    }

    @PostMapping("/unroll/{studentId}/{courseId}")
    public ResponseEntity<String> unrollStudentToCourse(@PathVariable long studentId, @PathVariable long courseId){
        var unrolled = enrollmentService.unrollStudentToCourse(studentId, courseId);
        return ResponseEntity.status(200).body(unrolled);
    }



}

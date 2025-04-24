package com.example.introspring.service;

import com.example.introspring.dto.EnrollmentDTO;

import java.util.List;

public interface EnrollmentService {

    EnrollmentDTO enrollStudentToCourse(long studentId, long courseId);
    EnrollmentDTO isStudentEnrolled(long studentId, long courseId);

    List<EnrollmentDTO> getEnrollmentsByCourse_Id(long courseId);

    String unrollStudentToCourse(long studentId, long courseId);
}

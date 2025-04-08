package com.example.introspring.service;

import com.example.introspring.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {

    void enrollStudentToCourse(long studentId, long courseId);
    Enrollment isStudentEnrolled(long studentId, long courseId);

    List<Enrollment> getEnrollmentsByCourse_Id(long courseId);

}

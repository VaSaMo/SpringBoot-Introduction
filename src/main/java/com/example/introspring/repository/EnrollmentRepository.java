package com.example.introspring.repository;

import com.example.introspring.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudent_Id(long studentId);
    List<Enrollment> findByCourse_Id(long courseId);

    Enrollment getEnrollmentByStudent_IdAndCourse_Id(long studentId, long courseId);

    boolean existsByStudent_IdAndCourse_Id(long studentId, long courseId);
    List<Enrollment> getEnrollmentsByCourse_Id(long courseId);

}


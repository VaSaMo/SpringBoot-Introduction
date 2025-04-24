package com.example.introspring.service.impl;
import com.example.introspring.dto.EnrollmentDTO;
import com.example.introspring.entity.Course;
import com.example.introspring.entity.Enrollment;
import com.example.introspring.entity.Student;
import com.example.introspring.mapper.EnrollmentMapper;
import com.example.introspring.repository.EnrollmentRepository;
import com.example.introspring.service.EnrollmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    EnrollmentMapper enrollmentMapper;

    @Override
    @Transactional
    public EnrollmentDTO enrollStudentToCourse(long studentId, long courseId){
        var course = new Course();
        course.setId(courseId);

        var student = new Student();
        student.setId(studentId);

        var enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);

        if(enrollmentRepository.existsByStudent_IdAndCourse_Id(studentId, courseId)){
            throw new RuntimeException("Student already enrolled in course");
        }

        return enrollmentMapper.toDTO(enrollmentRepository.save(enrollment));

    }

    @Override
    public EnrollmentDTO isStudentEnrolled(long studentId, long courseId){
        return enrollmentMapper.toDTO(enrollmentRepository.getEnrollmentByStudent_IdAndCourse_Id(studentId, courseId));
    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByCourse_Id(long courseId){
        return enrollmentRepository.getEnrollmentsByCourse_Id(courseId).stream().map(entity -> enrollmentMapper.toDTO(entity)).toList();
    }

    @Override
    public String unrollStudentToCourse(long studentId, long courseId){
        var enrollment = enrollmentRepository.getEnrollmentByStudent_IdAndCourse_Id(studentId, courseId);
        if(enrollment == null){
            throw new RuntimeException("Enrollment not found");
        }
        enrollmentRepository.delete(enrollment);
        return "Student with id "+studentId+" unrolled from course with id "+courseId;
    }



}

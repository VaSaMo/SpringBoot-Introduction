package com.example.introspring.service.impl;
import com.example.introspring.entity.Course;
import com.example.introspring.entity.Enrollment;
import com.example.introspring.entity.Student;
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

    @Override
    @Transactional
    public void enrollStudentToCourse(long studentId, long courseId){
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

        enrollmentRepository.save(enrollment);

    }

    @Override
    public Enrollment isStudentEnrolled(long studentId, long courseId){
        return enrollmentRepository.getEnrollmentByStudent_IdAndCourse_Id(studentId, courseId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse_Id(long courseId){
        return enrollmentRepository.getEnrollmentsByCourse_Id(courseId);
    }



}

package com.example.introspring.service;

import org.springframework.data.domain.Page;

import com.example.introspring.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;


public interface StudentService {


    Student createStudent(Student student);
    List<Student> getAllStudents();

    public List<Student> getByProgram(String program);

    Page<Student> findAll(int page);

    List<Student> listStudentsOfCourse(long courseId);

    int countByProgram(String program);

    Student getStudentById(long id);

    Student getStudentByCode(String code);
}

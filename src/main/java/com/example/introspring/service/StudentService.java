package com.example.introspring.service;

import com.example.introspring.dto.StudentDTO;
import org.springframework.data.domain.Page;

import com.example.introspring.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;


public interface StudentService {


    StudentDTO createStudent(StudentDTO student);
    List<StudentDTO> getAllStudents();

    public List<StudentDTO> getByProgram(String program);

    Page<StudentDTO> findAll(int page);

    List<StudentDTO> listStudentsOfCourse(long courseId);

    int countByProgram(String program);

    StudentDTO getStudentById(long id);

    StudentDTO getStudentByCode(String code);

    StudentDTO updateStudent(long studentId, StudentDTO student);
}

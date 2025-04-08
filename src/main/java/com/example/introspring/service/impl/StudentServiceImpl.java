package com.example.introspring.service.impl;

import com.example.introspring.entity.Student;
import com.example.introspring.repository.EnrollmentRepository;
import com.example.introspring.repository.StudentRepository;
import com.example.introspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    //@Value("${app.pagination.size}$") //${}$ : algo que especifico en el application.properties
    private int pageSize = 3;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Student createStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getByProgram(String program) {
        return studentRepository.findByProgram(program);
    }

    @Override
    public Page<Student> findAll(int page){
        Pageable pageable = PageRequest.of(page, pageSize);
        return studentRepository.findAll(pageable);
    }

    @Override
    public List<Student> listStudentsOfCourse(long courseId){
        var enrollments = enrollmentRepository.findByCourse_Id(courseId);
        var students = enrollments.stream().map(enrollment -> {
            return enrollment.getStudent(); //enrollment::getCourse => esa es otra forma de hacerlo, mas simple
        }).toList();
        return students;
    }

    @Override
    public int countByProgram(String program){
        return studentRepository.countByProgram(program);
    }

    @Override
    public Student getStudentById(long id){
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public Student getStudentByCode(String code){
        return studentRepository.findByCode(code).orElseThrow();
    }

}

package com.example.introspring.service.impl;

import com.example.introspring.dto.StudentDTO;
import com.example.introspring.entity.Student;
import com.example.introspring.mapper.StudentMapper;
import com.example.introspring.repository.EnrollmentRepository;
import com.example.introspring.repository.StudentRepository;
import com.example.introspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    StudentMapper studentMapper;


    @Override
    public StudentDTO createStudent(StudentDTO student) {
        return studentMapper.toDTO(studentRepository.save(studentMapper.toEntity(student)));
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(entity -> studentMapper.toDTO(entity)).toList();
    }

    @Override
    public List<StudentDTO> getByProgram(String program) {
        return studentRepository.findByProgram(program).stream().map(entity -> studentMapper.toDTO(entity)).toList();
    }

    @Override
    public Page<StudentDTO> findAll(int page){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("name").ascending());
        return studentRepository.findAll(pageable).map(entity -> studentMapper.toDTO(entity));
    }

    @Override
    public List<StudentDTO> listStudentsOfCourse(long courseId){
        var enrollments = enrollmentRepository.findByCourse_Id(courseId);
        var students = enrollments.stream().map(enrollment -> {
            return enrollment.getStudent(); //enrollment::getCourse => esa es otra forma de hacerlo, mas simple
        }).toList();
        return students.stream().map(entity -> studentMapper.toDTO(entity)).toList();
    }

    @Override
    public int countByProgram(String program){
        return studentRepository.countByProgram(program);
    }

    @Override
    public StudentDTO getStudentById(long id){
        return studentMapper.toDTO(studentRepository.findById(id).orElseThrow());
    }

    @Override
    public StudentDTO getStudentByCode(String code){
        return studentMapper.toDTO(studentRepository.findByCode(code).orElseThrow());
    }

    @Override
    public StudentDTO updateStudent(long studentId, StudentDTO student){
        Student found = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if(student.getName() != null){
            found.setName(student.getName());
        }

        if(student.getProgram() != null){
            found.setProgram(student.getProgram());
        }

        return studentMapper.toDTO(studentRepository.save(found));

    }

}

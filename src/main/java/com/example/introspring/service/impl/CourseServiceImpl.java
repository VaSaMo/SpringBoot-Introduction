package com.example.introspring.service.impl;

import com.example.introspring.dto.CourseDTO;
import com.example.introspring.dto.CourseWithCountDTO;
import com.example.introspring.entity.Course;
import com.example.introspring.mapper.CourseMapper;
import com.example.introspring.repository.CourseRepository;
import com.example.introspring.repository.EnrollmentRepository;
import com.example.introspring.repository.ProfesorRepository;
import com.example.introspring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private int pageSize = 3;

    @Autowired //autowirde: me permite inyectar la relacion
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    CourseMapper courseMapper;


    @Override
    public CourseDTO createCourse(CourseDTO course) {
        var entity = courseMapper.toEntity(course);
        if (courseRepository.existsByName(course.getName())) {
            throw new RuntimeException("Course already exists");
        }
        if(!profesorRepository.existsById(entity.getProfesor().getId())){
            throw new RuntimeException("Profesor does not exist");
        }
        return courseMapper.toDTO(courseRepository.save(entity));
    }


    @Override
    public List<CourseDTO> getAllCourses(){
        return courseRepository.findAll().stream().map(entity -> courseMapper.toDTO(entity)).toList();
    }

    @Override
    public List<CourseDTO> listCoursesOfStudent(long studentId){
        var enrollments = enrollmentRepository.findByStudent_Id(studentId);
        var courses = enrollments.stream().map(enrollment -> {
            return courseMapper.toDTO(enrollment.getCourse()); //enrollment::getCourse => esa es otra forma de hacerlo, mas simple
        }).toList();
        return courses;
    }

    @Override
    public List<CourseDTO> findByProfesor(long profesorId){
        return courseRepository.findByProfesor_Id(profesorId).stream().map(entity -> courseMapper.toDTO(entity)).toList();
    }

    @Override
    public CourseDTO getCourseById(long id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            return courseMapper.toDTO(course.get());
        }else{
            throw new RuntimeException("There is no course with id: " + id);
        }

        //Otra forma de hacerlo es:
        //return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("There is now course with id: " + id));
    }

    @Override
    public void deleteCourse(long courseId){
        courseRepository.deleteById(courseId);
    }

    @Override
    public Page<CourseDTO> searchCoursesByName(String name, int page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return courseRepository.findByNameContainingIgnoreCase(name, pageable).map(entity -> courseMapper.toDTO(entity));
    }

    @Override
    public Page<CourseWithCountDTO> coursesWithStudentCount(int page){
        Pageable pageable = PageRequest.of(page, pageSize);
        var courses = courseRepository.findAll(pageable);
        return courses.map(course ->
                new CourseWithCountDTO(
                        course.getId(),
                        course.getName(),
                        course.getEnrollments().size()
                )
        );
    }

}


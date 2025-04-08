package com.example.introspring.service.impl;

import com.example.introspring.entity.Course;
import com.example.introspring.repository.CourseRepository;
import com.example.introspring.repository.EnrollmentRepository;
import com.example.introspring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired //autowirde: me permite inyectar la relacion
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;


    @Override
    public Course createCourse(Course course){
        if(courseRepository.existsByName(course.getName())){
            throw new RuntimeException("Course already exists");
        }
        courseRepository.save(course);
        return course;
    }

    @Override
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    @Override
    public List<Course> listCoursesOfStudent(long studentId){
        var enrollments = enrollmentRepository.findByStudent_Id(studentId);
        var courses = enrollments.stream().map(enrollment -> {
            return enrollment.getCourse(); //enrollment::getCourse => esa es otra forma de hacerlo, mas simple
        }).toList();
        return courses;
    }

    @Override
    public List<Course> findByProfesor(long profesorId){
        return courseRepository.findByProfesor_Id(profesorId);
    }


    @Override
    public Course getCourseById(long id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            return course.get();
        }else{
            throw new RuntimeException("There is no course with id: " + id);
        }

        //Otra forma de hacerlo es:
        //return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("There is now course with id: " + id));
    }

    public void deleteCourse(long courseId){
        courseRepository.deleteById(courseId);
    }

}


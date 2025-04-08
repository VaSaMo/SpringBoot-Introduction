package com.example.introspring.controller;

import com.example.introspring.entity.Course;
import com.example.introspring.service.CourseService;
import com.example.introspring.service.ProfesorService;
import com.example.introspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ProfesorService professorService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("course", new Course());
        model.addAttribute("professors",professorService.getAllProfessor());
        return "course";
    }


    @PostMapping
    public String createCourse(@ModelAttribute Course course){
        courseService.createCourse(course);
        return "redirect:/course";
    }

    @GetMapping()
    public String getCourses(Model model){
        var courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") long id){
        var course = courseService.getCourseById(id);
        var students = studentService.listStudentsOfCourse(id);
        model.addAttribute("course", course);
        model.addAttribute("students",students);
        return "coursedetail";
    }



}

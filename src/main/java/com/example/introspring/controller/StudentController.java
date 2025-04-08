package com.example.introspring.controller;

import com.example.introspring.entity.Student;
import com.example.introspring.service.CourseService;
import com.example.introspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String student(Model model){
        var students= studentService.getAllStudents();
        model.addAttribute("greeting", "Hola mundo");
        model.addAttribute("students", students);
        model.addAttribute("student",new Student());
        return "student";
    }
    
    @PostMapping
    public String saveStudent(@ModelAttribute Student student){
        //Almacenar
        studentService.createStudent(student);
        return "redirect:/student";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") long id){
        var student = studentService.getStudentById(id);
        var courses = courseService.listCoursesOfStudent(id);
        model.addAttribute("student", student);
        model.addAttribute("courses",courses);
        return "studentdetail";
    }


}

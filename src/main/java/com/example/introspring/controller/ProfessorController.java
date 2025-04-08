package com.example.introspring.controller;

import com.example.introspring.entity.Course;
import com.example.introspring.service.CourseService;
import com.example.introspring.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profesor")
public class ProfessorController {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") long id){
        var profesor = profesorService.getProfessorById(id);
        var courses= courseService.findByProfesor(id);
        model.addAttribute("profesor", profesor);
        model.addAttribute("courses", courses);
        return "profesordetail";
    }
}

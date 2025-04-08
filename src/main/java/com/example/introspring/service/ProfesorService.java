package com.example.introspring.service;

import com.example.introspring.entity.Profesor;

import java.util.List;

public interface ProfesorService {

    List<Profesor> getAllProfessor();

    Profesor getProfessorById(long id);

}

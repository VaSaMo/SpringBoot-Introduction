package com.example.introspring.service.impl;

import com.example.introspring.entity.Profesor;
import com.example.introspring.repository.ProfesorRepository;
import com.example.introspring.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public List<Profesor> getAllProfessor(){
        return profesorRepository.findAll();
    }

    @Override
    public Profesor getProfessorById(long id) {
        return profesorRepository.findById(id).orElseThrow();
    }



}

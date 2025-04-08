package com.example.introspring.repository;

import com.example.introspring.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByCode(String code);
    List<Student> findByProgram(String program);

    Page<Student> findAll(Pageable pageable);

    int countByProgram(String program);


}
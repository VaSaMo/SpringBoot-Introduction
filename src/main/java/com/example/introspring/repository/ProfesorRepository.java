package com.example.introspring.repository;

import com.example.introspring.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    Optional<Profesor> findById(long id);

    boolean existsById(long id);

}

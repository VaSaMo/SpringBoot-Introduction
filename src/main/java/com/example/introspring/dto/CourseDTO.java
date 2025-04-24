package com.example.introspring.dto;

public class CourseDTO {

    private long id;
    private String name;
    private Long professorId; // solo el ID del profesor

    public CourseDTO(long id, String name, Long professorId) {
        this.id = id;
        this.name = name;
        this.professorId = professorId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }
}

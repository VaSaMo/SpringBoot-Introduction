package com.example.introspring.dto;

import com.example.introspring.entity.Enrollment;

public class EnrollmentDTO {

    private long id;
    private long courseId;
    private long studentId;

    public EnrollmentDTO(long id, long courseId, long studentId) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}

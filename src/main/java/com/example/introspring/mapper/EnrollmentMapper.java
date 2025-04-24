package com.example.introspring.mapper;

import com.example.introspring.dto.EnrollmentDTO;
import com.example.introspring.dto.StudentDTO;
import com.example.introspring.entity.Enrollment;
import com.example.introspring.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "course.id", target = "courseId")
    EnrollmentDTO toDTO(Enrollment enrollment);

    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "courseId", target = "course.id")
    Enrollment toEntity(EnrollmentDTO dto);

    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "courseId", target = "course.id")
    void updateEntityFromDTO(EnrollmentDTO dto, @MappingTarget Enrollment enrollment);


}

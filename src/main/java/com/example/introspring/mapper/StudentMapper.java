package com.example.introspring.mapper;

import com.example.introspring.dto.CourseDTO;
import com.example.introspring.dto.StudentDTO;
import com.example.introspring.entity.Course;
import com.example.introspring.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {


    StudentDTO toDTO(Student student);

    Student toEntity(StudentDTO dto);

    void updateEntityFromDTO(StudentDTO dto, @MappingTarget Student student);


}

package com.example.introspring.mapper;

import com.example.introspring.dto.CourseDTO;
import com.example.introspring.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "profesor.id", target = "professorId")
    CourseDTO toDTO(Course course);

    @Mapping(source = "professorId", target = "profesor.id")
    Course toEntity(CourseDTO dto);

    @Mapping(source = "professorId", target = "profesor.id")
    void updateEntityFromDTO(CourseDTO dto, @MappingTarget Course course);


}
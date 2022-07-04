package com.example.demo.college.mapper;

import com.example.demo.college.model.dto.CollegeDto;
import com.example.demo.college.model.entity.College;

public interface CollegeMapper {

    CollegeDto toDto(College college);

    College toEntity(CollegeDto collegeDto);
    
}

package com.example.demo.college.mapper;

import com.example.demo.college.model.dto.CollegeDto;
import com.example.demo.college.model.entity.College;
import org.springframework.stereotype.Component;

@Component
public class CollegeMapperImpl implements CollegeMapper {

    @Override
    public CollegeDto toDto(College college) {
        CollegeDto collegeDto = new CollegeDto();
        collegeDto.setName(college.getName());
        collegeDto.setDescription(college.getDescription());
        collegeDto.setId(college.getId());
        return collegeDto;
    }

    @Override
    public College toEntity(CollegeDto collegeDto) {
        College college = new College();
        college.setName(collegeDto.getName());
        college.setDescription(collegeDto.getDescription());
        college.setId(collegeDto.getId());
        return college;
    }
        

}

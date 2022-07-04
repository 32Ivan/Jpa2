package com.example.demo.college.service;

import com.example.demo.college.model.dto.CollegeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CollegeService {

    CollegeDto getCollegeById(Long id);

    CollegeDto saveCollege(CollegeDto studentDto);

    List<CollegeDto> saveColleges(List<CollegeDto> studentDto);
    
    List<CollegeDto> getAllColleges();
    
    void deleteCollege(Long id);

    Page<CollegeDto> getAllCollegePageByName(Integer pageNumber, Integer pageSize, String name);
}

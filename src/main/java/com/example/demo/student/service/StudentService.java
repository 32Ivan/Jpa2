package com.example.demo.student.service;

import com.example.demo.student.model.dto.StudentCollegeDto;
import com.example.demo.student.model.dto.StudentDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    StudentDto getStudentById(Long id);
    
    StudentDto saveStudent(StudentDto studentDto);

    StudentDto saveStudentCollege(StudentCollegeDto studentCollageDto);

    List<StudentDto> saveStudents(List<StudentDto> studentDto);
    
    List<StudentDto> getAllStudents();
    
    void deleteStudent(Long id);

    Page<StudentDto> getAllStudentPageByName(Integer pageNumber, Integer pageSize, String name);

}

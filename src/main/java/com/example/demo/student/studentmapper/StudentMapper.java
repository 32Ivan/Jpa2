package com.example.demo.student.studentmapper;

import com.example.demo.student.model.dto.StudentCollegeDto;
import com.example.demo.college.model.entity.College;
import com.example.demo.student.model.entity.Student;
import com.example.demo.student.model.dto.StudentDto;

import java.util.Optional;

public interface StudentMapper {

    StudentDto toDto(Student student);

    Student toEntity(StudentDto studentDto);


    Student toEntityCollege(StudentCollegeDto StudentCollegeDto, Optional<College> optionalCollege);
    
}

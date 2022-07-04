package com.example.demo.student.studentmapper;

import com.example.demo.address.mapper.AddressMapper;
import com.example.demo.college.mapper.CollegeMapper;
import com.example.demo.address.dto.AddressDto;
import com.example.demo.college.model.dto.CollegeDto;
import com.example.demo.student.model.dto.StudentCollegeDto;
import com.example.demo.student.model.dto.StudentDto;
import com.example.demo.address.entity.Address;
import com.example.demo.college.model.entity.College;
import com.example.demo.student.model.entity.Student;
import com.example.demo.college.collegeRepo.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentMapperImpl implements StudentMapper {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private CollegeRepository collegeRepository;

    @Override
    public StudentDto toDto(Student student) {

        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setAge(student.getAge());
        studentDto.setEmail(student.getEmail());
        studentDto.setId(student.getId());

        if (student.getAddress() != null) {
            AddressDto addressDto = addressMapper.toDto(student.getAddress());
            studentDto.setAddress(addressDto);
        }

        if (student.getCollege() != null) {
            CollegeDto collegeDto = collegeMapper.toDto(student.getCollege());
            studentDto.setCollege(collegeDto);
        }

        return studentDto;
    }

    @Override
    public Student toEntity(StudentDto studentDto) {

        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setAge(studentDto.getAge());
        student.setEmail(studentDto.getEmail());
        student.setId(studentDto.getId());

        if (studentDto.getAddress() != null) {
            Address address = addressMapper.toEntity(studentDto.getAddress());
            student.setAddress(address);
        }

        if (studentDto.getCollege() != null) {
            College college = collegeMapper.toEntity(studentDto.getCollege());
            student.setCollege(college);
        }

        return student;
    }

    @Override
    public Student toEntityCollege(StudentCollegeDto studentCollegeDto ,Optional<College> optionalCollege) {

        Student student = new Student();
        student.setFirstName(studentCollegeDto.getFirstName());
        student.setLastName(studentCollegeDto.getLastName());
        student.setAge(studentCollegeDto.getAge());
        student.setEmail(studentCollegeDto.getEmail());
        student.setId(studentCollegeDto.getId());

        if (studentCollegeDto.getAddress() != null) {
            Address address = addressMapper.toEntity(studentCollegeDto.getAddress());
            student.setAddress(address);
        }

        optionalCollege.ifPresent(student::setCollege);

        return student;
    }

}

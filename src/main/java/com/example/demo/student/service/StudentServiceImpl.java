package com.example.demo.student.service;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.student.studentmapper.StudentMapper;
import com.example.demo.student.model.dto.StudentCollegeDto;
import com.example.demo.student.model.dto.StudentDto;
import com.example.demo.college.model.entity.College;
import com.example.demo.student.model.entity.Student;
import com.example.demo.college.collegeRepo.CollegeRepository;
import com.example.demo.student.studentRepo.StudentRepository;
import com.example.demo.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//odvija logiku i komunicira sa repo a repo ide na bazu 
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    private final EmailService emailService;
    private final CollegeRepository collegeRepository;

    @Override
    public StudentDto getStudentById(Long id) {
        return studentMapper.toDto(studentRepository.findById(id).orElseThrow(() -> new ApiRequestException("Puca")));
    }

    @Override
    @Transactional
    public StudentDto saveStudent(StudentDto studentDto) {
        try {
            Student student = studentMapper.toEntity(studentDto);
            Optional<College> byIdCollege = collegeRepository.findById(studentDto.getCollege().getId());
            byIdCollege.ifPresent(student::setCollege);
//            Optional<College> c = collegeRepository.findById(1L);
//            student.setCollege(c.orElse(null));
            return studentMapper.toDto(studentRepository.save(student));
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public StudentDto saveStudentCollege(StudentCollegeDto studentCollegeDto) {
        try {
            Optional<College> byIdCollege = collegeRepository.findById(studentCollegeDto.getCollegeId());
            Student student = studentMapper.toEntityCollege(studentCollegeDto, byIdCollege);
            byIdCollege.ifPresent(student::setCollege);
//            Optional<College> c = collegeRepository.findById(1L);
//            student.setCollege(c.orElse(null));
            return studentMapper.toDto(studentRepository.save(student));
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public List<StudentDto> getAllStudents() {
        try {
            return studentRepository.findAll().stream().map(studentMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<StudentDto> saveStudents(List<StudentDto> studentsDto) {
        try {
            studentRepository.saveAll(studentsDto.stream().map(studentMapper::toEntity).collect(Collectors.toList()));
            return getAllStudents();
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public Page<StudentDto> getAllStudentPageByName(Integer pageNumber, Integer pageSize, String name) {
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("Sva polja moraju biti popunjena !");
        }
        try {
            return studentRepository.findByFirstName(name, PageRequest.of(pageNumber, pageSize)).map(studentMapper::toDto);
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
            
        }
    }
    
}

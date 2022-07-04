package com.example.demo.college.service;

import com.example.demo.college.mapper.CollegeMapper;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.college.model.dto.CollegeDto;
import com.example.demo.college.model.entity.College;
import com.example.demo.college.collegeRepo.CollegeRepository;
import com.example.demo.email.service.EmailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//odvija logiku i komunicira sa repo a repo ide na bazu 
@Service
@Transactional(readOnly = true)
public class CollegeServiceImpl implements CollegeService {


    public CollegeServiceImpl(CollegeMapper collegeMapper, CollegeRepository collegeRepository, EmailService emailService) {
        this.collegeMapper = collegeMapper;
        this.collegeRepository = collegeRepository;
        this.emailService = emailService;
    }

    private final CollegeMapper collegeMapper;
    private final CollegeRepository collegeRepository;

    private final EmailService emailService;

    @Override
    public CollegeDto getCollegeById(Long id) {
        return collegeMapper.toDto(collegeRepository.findById(id).orElseThrow(() -> new ApiRequestException("Puca")));
    }

    @Override
    @Transactional
    public CollegeDto saveCollege(CollegeDto collegeDto) {
        try {
            College college = collegeMapper.toEntity(collegeDto);
            return collegeMapper.toDto(collegeRepository.save(college));
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public List<CollegeDto> getAllColleges() {
        try {
            return collegeRepository.findAll().stream().map(collegeMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<CollegeDto> saveColleges(List<CollegeDto> collegeDto) {
        try {
            collegeRepository.saveAll(collegeDto.stream().map(collegeMapper::toEntity).collect(Collectors.toList()));
            return getAllColleges();
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteCollege(Long id) {
        try {
            collegeRepository.deleteById(id);
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public Page<CollegeDto> getAllCollegePageByName(Integer pageNumber, Integer pageSize, String name) {
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("Sva polja moraju biti popunjena !");
        }
        try {
            return collegeRepository.findByName(name, PageRequest.of(pageNumber, pageSize)).map(collegeMapper::toDto);
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
            
        }
    }

}

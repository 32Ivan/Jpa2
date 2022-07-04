package com.example.demo.student.model.dto;

import com.example.demo.address.dto.AddressDto;
import com.example.demo.college.model.dto.CollegeDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StudentDto {

    @NotBlank(message = "Is mandatory")
    private String firstName;

    @NotBlank(message = "Is mandatory")
    private String lastName;

    @NotBlank(message = "Is mandatory")
    private String email;

    private Integer age;

    private Long id;

    private AddressDto address;

    private CollegeDto college;

}

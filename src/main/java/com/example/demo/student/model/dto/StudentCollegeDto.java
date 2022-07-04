package com.example.demo.student.model.dto;

import com.example.demo.address.dto.AddressDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StudentCollegeDto {

    @NotBlank(message = "Is mandatory")
    private String firstName;

    @NotBlank(message = "Is mandatory")
    private String lastName;

    @NotBlank(message = "Is mandatory")
    private String email;

    private Integer age;

    private Long id;

    private AddressDto address;

    @NotNull
    private Long collegeId;
}

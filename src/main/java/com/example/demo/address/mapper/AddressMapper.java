package com.example.demo.address.mapper;

import com.example.demo.address.dto.AddressDto;
import com.example.demo.address.entity.Address;

public interface AddressMapper {

    AddressDto toDto(Address address);

    Address toEntity(AddressDto addressDto);
    
}

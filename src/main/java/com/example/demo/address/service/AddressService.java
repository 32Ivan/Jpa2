package com.example.demo.address.service;

import com.example.demo.address.dto.AddressDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AddressService {

    AddressDto getAddressById(Long id);

    AddressDto saveAddress(AddressDto addressDto);

    List<AddressDto> saveAddressList(List<AddressDto> addressDto);
    
    List<AddressDto> getAllAddress();
    
    void deleteAddress(Long id);

    Page<AddressDto> getAllAddressPageByName(Integer pageNumber, Integer pageSize, String city);
}

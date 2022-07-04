package com.example.demo.address.mapper;

import com.example.demo.address.dto.AddressDto;
import com.example.demo.address.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDto toDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity(address.getCity());
        addressDto.setAddressType(address.getAddressType());
        addressDto.setId(address.getId());
        return addressDto;
    }

    @Override
    public Address toEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setAddressType(addressDto.getAddressType());
        address.setId(addressDto.getId());
        return address;
    }

}

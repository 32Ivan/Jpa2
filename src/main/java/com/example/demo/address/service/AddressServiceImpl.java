package com.example.demo.address.service;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.address.mapper.AddressMapper;
import com.example.demo.address.dto.AddressDto;
import com.example.demo.address.entity.Address;
import com.example.demo.address.dao.AddressRepository;
import com.example.demo.email.service.EmailService;
import com.example.demo.logg.LoggingController;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//odvija logiku i komunicira sa repo a repo ide na bazu 
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    
    private final AddressMapper addressMapper;
    
    private final AddressRepository addressRepository;

    private final EmailService emailService;

    Logger logger = LoggerFactory.getLogger(AddressService.class);

    @Override
    public AddressDto getAddressById(Long id) {
        return addressMapper.toDto(addressRepository.findById(id).orElseThrow(() -> new ApiRequestException("Puca")));
    }

    @Override
    @Transactional
    public AddressDto saveAddress(AddressDto addressDto) {
        try {
            Address address = addressMapper.toEntity(addressDto);
            return addressMapper.toDto(addressRepository.save(address));
        } catch (Exception e) {
            emailService.sendEmailError();
            logger.error("An ERROR Message");
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public List<AddressDto> getAllAddress() {
        try {
            return addressRepository.findAll().stream().map(addressMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<AddressDto> saveAddressList(List<AddressDto> addressDto) {
        try {
            addressRepository.saveAll(addressDto.stream().map(addressMapper::toEntity).collect(Collectors.toList()));
            return getAllAddress();
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {
        try {
            addressRepository.deleteById(id);
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public Page<AddressDto> getAllAddressPageByName(Integer pageNumber, Integer pageSize, String city) {
        if (StringUtils.isEmpty(city)) {
            throw new RuntimeException("Sva polja moraju biti popunjena !");
        }
        try {
            return addressRepository.findByCity(city, PageRequest.of(pageNumber, pageSize)).map(addressMapper::toDto);
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
            
        }
    }

}

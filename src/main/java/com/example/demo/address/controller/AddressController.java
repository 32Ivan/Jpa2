package com.example.demo.address.controller;

import com.example.demo.address.dto.AddressDto;
import com.example.demo.address.service.AddressService;
import com.example.demo.logg.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//1. kontroler spajanje sa klientom za api pozive
//2. servis za logiku i komunikaciju izmedu kontrolera i repo, servis mora vracati dtoove
//3. repo spanjanje na bazu 
//4. mapper za pretvaranje dto u entitet i obrnuto, opcenito mappiranja java klasa

//Api prica sa Klienton , servisni sloj za logiku spaja se kontroler , servisni ide na repo i repo ide na bazu 
@RequestMapping("address")
@RestController
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(value = "find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AddressDto>> addressFindAll() {
        return ResponseEntity.ok(this.addressService.getAllAddress());
    }

    @GetMapping(value = "ID/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDto> addressId(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @PutMapping("update")
    public ResponseEntity<AddressDto> addressUpdate(@RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.saveAddress(addressDto));
    }

    @PostMapping("save")
    public ResponseEntity<AddressDto> saveAddress(@Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.saveAddress(addressDto));
    }

    @PostMapping("saveAll")
    public ResponseEntity<List<AddressDto>> saveAddressList(@RequestBody List<AddressDto> addressDto) {
        return ResponseEntity.ok(addressService.saveAddressList(addressDto));
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        this.addressService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "page/pageNumber={pageNumber}/pageSize={pageSize}/city={city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AddressDto>> getAllAddressPages(
            @Valid
            @PathVariable(required = true) Integer pageNumber,
            @PathVariable(required = true) Integer pageSize,
            @PathVariable(required = true) String city
    ) {
        return ResponseEntity.ok(addressService.getAllAddressPageByName(pageNumber, pageSize, city));
    }

}

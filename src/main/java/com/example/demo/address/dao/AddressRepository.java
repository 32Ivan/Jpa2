package com.example.demo.address.dao;

import com.example.demo.address.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//komunicira sa bazon i spaja se na servis 
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findById(Long id);

    List<Address> findAll();

    void deleteById(Long id);

    Page<Address> findByCity(String city, Pageable pageable);
    

}

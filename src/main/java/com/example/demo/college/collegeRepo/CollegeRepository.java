package com.example.demo.college.collegeRepo;

import com.example.demo.college.model.entity.College;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//komunicira sa bazon i spaja se na servis 
@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {

    Optional<College> findById(String id);

    List<College> findAll();

    void deleteById(Long id);

    Page<College> findByName(String name, Pageable pageable);

}

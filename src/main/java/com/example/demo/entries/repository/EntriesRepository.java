package com.example.demo.entries.repository;

import com.example.demo.entries.model.entity.Entries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//komunicira sa bazon i spaja se na servis 
@Repository
public interface EntriesRepository extends JpaRepository<Entries, Long> {

    List<Entries> findAll();

}

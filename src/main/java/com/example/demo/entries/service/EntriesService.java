package com.example.demo.entries.service;

import com.example.demo.entries.model.dto.EntriesDto;

import java.util.List;

public interface EntriesService {

    List<EntriesDto> getAllEntries();

    List<EntriesDto> saveEntries();

}

package com.example.demo.entries.mapper;

import com.example.demo.entries.model.dto.EntriesDto;
import com.example.demo.entries.model.entity.Entries;

public interface EntriesMapper {

    EntriesDto toDto(Entries entries);

    Entries toEntity(EntriesDto entriesDto);

}

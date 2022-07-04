package com.example.demo.entries.mapper;

import com.example.demo.entries.mapper.EntriesMapper;
import com.example.demo.entries.model.dto.EntriesDto;
import com.example.demo.entries.model.entity.Entries;
import org.springframework.stereotype.Component;

@Component
public class EntriesMapperImpl implements EntriesMapper {

    @Override
    public EntriesDto toDto(Entries entries) {
        EntriesDto entriesDto = new EntriesDto();
        entriesDto.setId(entries.getId());
        entriesDto.setUser_id(entries.getUser_id());
        entriesDto.setTitle(entries.getTitle());
        entriesDto.setBody(entries.getBody());
        return entriesDto;
    }

    @Override
    public Entries toEntity(EntriesDto entriesDto) {
        Entries entries = new Entries();
        entries.setId(entriesDto.getId());
        entries.setUser_id(entriesDto.getUser_id());
        entries.setTitle(entriesDto.getTitle());
        entries.setBody(entriesDto.getBody());
        return entries;
    }



}

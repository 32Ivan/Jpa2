package com.example.demo.entries.service;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.entries.mapper.EntriesMapper;
import com.example.demo.entries.model.dto.EntriesDto;
import com.example.demo.entries.repository.EntriesRepository;
import com.example.demo.email.service.EmailService;
import com.example.demo.logg.LoggingController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

//odvija logiku i komunicira sa repo a repo ide na bazu 
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EntriesServiceImpl implements EntriesService {
    

    private final EntriesMapper entriesMapper;

    private final EmailService emailService;

    private final EntriesRepository entriesRepository;

    private final WebClient webClient;

    Logger logger = LoggerFactory.getLogger(LoggingController.class);
    @Override
    public List<EntriesDto> getAllEntries() {
        try {
            return entriesRepository.findAll().stream().map(entriesMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
        }
    }


    @Override
    @Transactional
    public List<EntriesDto> saveEntries() {
        try {
            String uri = "https://gorest.co.in/public/v2/posts";
            List<EntriesDto> respons = webClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<EntriesDto>>() {})
                    .block();
            logger.warn("A WARN Message");
            entriesRepository.saveAll(respons.stream().map(entriesMapper::toEntity).collect(Collectors.toList()));
            return getAllEntries();
        } catch (Exception e) {
            emailService.sendEmailError();
            throw new ApiRequestException(e.getMessage());
        }
    }
}

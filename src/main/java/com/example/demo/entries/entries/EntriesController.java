package com.example.demo.entries.entries;

import com.example.demo.entries.model.dto.EntriesDto;
import com.example.demo.entries.repository.EntriesRepository;
import com.example.demo.entries.service.EntriesService;
import com.example.demo.logg.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
//1. kontroler spajanje sa klientom za api pozive
//2. servis za logiku i komunikaciju izmedu kontrolera i repo, servis mora vracati dtoove
//3. repo spanjanje na bazu 
//4. mapper za pretvaranje dto u entitet i obrnuto, opcenito mappiranja java klasa

//Api prica sa Klienton , servisni sloj za logiku spaja se kontroler , servisni ide na repo i repo ide na bazu 
@RequestMapping("entries")
@RestController
public class EntriesController {

    private final EntriesService entriesService;

    private final EntriesRepository entriesRepository;

    private final WebClient webClient;


    public EntriesController(EntriesService entriesService, EntriesRepository entriesRepository, WebClient webClient) {
        this.entriesService = entriesService;
        this.entriesRepository = entriesRepository;
        this.webClient = webClient;
    }


    @GetMapping(value = "find-entries", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntriesDto>> entriesFindAll() {
        return ResponseEntity.ok(this.entriesService.getAllEntries());
    }

    @GetMapping("saveAll")
    public ResponseEntity<List<EntriesDto>> saveEnt() {
        return ResponseEntity.ok(entriesService.saveEntries());
    }



//    @GetMapping(value = "/calling")
//    private String getCall(){
//        String uri = "https://api.publicapis.org/entries";
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri,String.class);
//        return result;
//    }
//
//    @GetMapping(value = "/calling2")
//    private String getCallWebClient(){
//        String uri = "https://api.publicapis.org/entries";
//        return webClient.get()
//                .uri(uri)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }
    
}

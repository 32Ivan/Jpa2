package com.example.demo.college.collegecontroller;

import com.example.demo.college.model.dto.CollegeDto;
import com.example.demo.college.service.CollegeService;
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
@RequestMapping("college")
@RestController
public class CollegeController {

    private final CollegeService collegeService;

    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping(value = "find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CollegeDto>> collegeFindAll() {
        return ResponseEntity.ok(this.collegeService.getAllColleges());
    }

    @GetMapping(value = "ID/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollegeDto> collegeId(@PathVariable Long id) {
        return ResponseEntity.ok(collegeService.getCollegeById(id));
    }

    @PutMapping("update")
    public ResponseEntity<CollegeDto> collegeUpdate(@RequestBody CollegeDto collegeDto) {
        return ResponseEntity.ok(collegeService.saveCollege(collegeDto));
    }

    @PostMapping("save")
    public ResponseEntity<CollegeDto> saveCollege(@Valid @RequestBody CollegeDto collegeDto) {
        return ResponseEntity.ok(collegeService.saveCollege(collegeDto));
    }

    @PostMapping("saveAll")
    public ResponseEntity<List<CollegeDto>> saveColleges(@RequestBody List<CollegeDto> collegeDto) {
        return ResponseEntity.ok(collegeService.saveColleges(collegeDto));
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCollege(@PathVariable Long id) {
        this.collegeService.deleteCollege(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "page/pageNumber={pageNumber}/pageSize={pageSize}/name={name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CollegeDto>> getAllCollegePages(
            @Valid
            @PathVariable(required = true) Integer pageNumber,
            @PathVariable(required = true) Integer pageSize,
            @PathVariable(required = true) String name
    ) {
        return ResponseEntity.ok(collegeService.getAllCollegePageByName(pageNumber, pageSize, name));
    }

}

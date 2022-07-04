package com.example.demo.student.studentcontroller;

import com.example.demo.student.model.dto.StudentCollegeDto;
import com.example.demo.student.model.dto.StudentDto;
import com.example.demo.student.studentRepo.StudentRepository;
import com.example.demo.student.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;
import java.util.List;
//1. kontroler spajanje sa klientom za api pozive
//2. servis za logiku i komunikaciju izmedu kontrolera i repo, servis mora vracati dtoove
//3. repo spanjanje na bazu 
//4. mapper za pretvaranje dto u entitet i obrnuto, opcenito mappiranja java klasa

//Api prica sa Klienton , servisni sloj za logiku spaja se kontroler , servisni ide na repo i repo ide na bazu 
@RequestMapping("student")
@RestController
public class StudentController {

    private final StudentService studentService;
    
    private final StudentRepository studentRepository;
    
    private final WebClient webClient;

    public StudentController(StudentService studentService, StudentRepository studentRepository, WebClient webClient) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
        this.webClient = webClient;
    }

//    @PostMapping("/saveEmployees")
//    public ResponseEntity<String> saveStudent(@RequestBody List<Student> student) {
//        studentRepository.saveAll(student);
//        return ResponseEntity.ok("Data saved");
//    }

    @GetMapping(value = "find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDto>> studentFindAll() {
        return ResponseEntity.ok(this.studentService.getAllStudents());
    }
    

    @GetMapping(value = "ID/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> studentId(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("update")
    public ResponseEntity<StudentDto> studentUpdate(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.saveStudent(studentDto));
    }

    @PostMapping("save")
    public ResponseEntity<StudentDto> saveStudent(@Valid @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.saveStudent(studentDto));
    }
    @PostMapping("saveWithCollege")
    public ResponseEntity<StudentDto> saveStudentWithCollege(@Valid @RequestBody StudentCollegeDto studentCollegeDto) {
        return ResponseEntity.ok(studentService.saveStudentCollege(studentCollegeDto));
    }

    @PostMapping("saveAll")
    public ResponseEntity<List<StudentDto>> saveStudents(@RequestBody List<StudentDto> students) {
        return ResponseEntity.ok(studentService.saveStudents(students));
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        this.studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "page/pageNumber={pageNumber}/pageSize={pageSize}/name={name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<StudentDto>> getAllStudentsPages(
            @Valid
            @PathVariable(required = true) Integer pageNumber,
            @PathVariable(required = true) Integer pageSize,
            @PathVariable(required = true) String name
    ) {
        return ResponseEntity.ok(studentService.getAllStudentPageByName(pageNumber, pageSize, name));
    }

    @GetMapping(value = "/calling")
    private String getCall(){
        String uri = "https://api.publicapis.org/entries";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri,String.class);
        return result;
    }

    @GetMapping(value = "/calling2")
    private String getCallWebClient(){
        String uri = "https://api.publicapis.org/entries";
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    
}

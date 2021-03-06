package com.example.PersonCRUD.controller;

import com.example.PersonCRUD.Dto.PersonDto;
import com.example.PersonCRUD.entity.Person;
import com.example.PersonCRUD.entity.apiResponse.ApiResponse;
import com.example.PersonCRUD.repository.PersonRepository;
import com.example.PersonCRUD.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @PreAuthorize(value = "hasAuthority('ADD')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPerson(@RequestBody PersonDto personDto) {
        ApiResponse apiResponse = personService.addPerson(personDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> editPerson(@RequestBody PersonDto personDto, @PathVariable Integer id) {
        ApiResponse apiResponse = personService.editPerson(personDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('READ_ALL')")
    @GetMapping("/get")
    public List<Person> getPersonList() {
        List<Person> personList = personRepository.findAll();
        return personList;
    }

    @PreAuthorize(value = "hasAuthority('GET_ONE')")
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getPersonById(@PathVariable Integer id){
        ApiResponse person = personService.getPerson(id);
        return ResponseEntity.ok(person);
    }

    @PreAuthorize(value = "hasAuthority('DELETE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deletePerson(@PathVariable Integer id) {
        ApiResponse apiResponse = personService.deletePerson(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}

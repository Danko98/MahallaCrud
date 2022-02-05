package com.example.PersonCRUD.controller;

import com.example.PersonCRUD.entity.Person;
import com.example.PersonCRUD.repository.PersonRepository;
import com.example.PersonCRUD.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Controller
public class MainController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @GetMapping("/")
    public List<Person> getIndex() {

        List<Person> personList = personService.getPersonList();

        return personList;



    }

}

package com.example.PersonCRUD.service;

import com.example.PersonCRUD.Dto.PersonDto;
import com.example.PersonCRUD.entity.Home;
import com.example.PersonCRUD.entity.Person;
import com.example.PersonCRUD.entity.apiResponse.ApiResponse;
import com.example.PersonCRUD.repository.HomeRepository;
import com.example.PersonCRUD.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    HomeRepository homeRepository;

    public ApiResponse addPerson(PersonDto personDto) {
        if (! personRepository.existsByFirstNameAndLastNameAndAgeAndHomeId(personDto.getFirstName(),personDto.getLastName(),personDto.getAge(),personDto.getHomeId())) {
            return new ApiResponse("Bunday adam bu uyda mavjud!!!",false);
        }

        if (!homeRepository.existsById(personDto.getHomeId())) {
            return new ApiResponse("Bunday uy topilmadi!!!",false);
        }

        Optional<Home> optionalHome = homeRepository.findById(personDto.getHomeId());

        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setAge(personDto.getAge());
        person.setHome(optionalHome.get());
        personRepository.save(person);

        return new ApiResponse("Inson saqlandi!!!", true);
    }

    public ApiResponse editPerson(PersonDto personDto, Integer id) {
        if (!personRepository.existsById(id)) {
            return new ApiResponse("Bunday Inson topilmadi!!!",false);
        }

        if (personRepository.existsByFirstNameAndLastNameAndAgeAndHomeId(personDto.getFirstName(),personDto.getLastName(),personDto.getAge(),personDto.getHomeId())) {
            return new ApiResponse("Bunday adam bu uyda mavjud!!!",false);
        }

        if (!homeRepository.existsById(personDto.getHomeId())) {
            return new ApiResponse("Bunday uy topilmadi!!!",false);
        }

        Optional<Home> optionalHome = homeRepository.findById(personDto.getHomeId());
        Optional<Person> optionalPerson = personRepository.findById(id);

        Person person = optionalPerson.get();
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setAge(personDto.getAge());
        person.setHome(optionalHome.get());
        personRepository.save(person);

        return new ApiResponse("Inson tahrirlandi!!!", true);
    }

    public ApiResponse getPerson(Integer id) {
        if (!personRepository.existsById(id)) {
            return new ApiResponse("Bunday Inson topilmadi!!!",false);
        }
        Optional<Person> optionalPerson = personRepository.findById(id);
        return new ApiResponse(optionalPerson.get());
    }

    public ApiResponse deletePerson(Integer id) {
        if (!personRepository.existsById(id)) {
            return new ApiResponse("Bunday Inson topilmadi!!!",false);
        }
        personRepository.deleteById(id);
        return new ApiResponse("Inson ro'yxatdan o'chirildi!!!",true);
    }

    public List<Person> getPersonList() {
        List<Person> personList = personRepository.findAll();
        return personList;
    }


}

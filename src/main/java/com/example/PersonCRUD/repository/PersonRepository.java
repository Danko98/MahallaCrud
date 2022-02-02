package com.example.PersonCRUD.repository;

import com.example.PersonCRUD.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    boolean existsByFirstNameAndLastNameAndAgeAndHomeId(String firstName, String lastName, Integer age, Integer homeId);
}

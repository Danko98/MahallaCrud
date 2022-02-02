package com.example.PersonCRUD.repository;

import com.example.PersonCRUD.entity.Home;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeRepository extends JpaRepository<Home, Integer> {

    boolean existsByName(String name);

}

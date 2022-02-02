package com.example.PersonCRUD.repository;

import com.example.PersonCRUD.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Integer> {

boolean existsByName(String name);

}

package com.example.PersonCRUD.repository;

import com.example.PersonCRUD.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    boolean existsByName(String name);
}

package com.example.PersonCRUD.controller;

import com.example.PersonCRUD.entity.Region;
import com.example.PersonCRUD.entity.apiResponse.ApiResponse;
import com.example.PersonCRUD.repository.RegionRepository;
import com.example.PersonCRUD.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/region")
public class RegionController {

    @Autowired
    RegionService regionService;

    @Autowired
    RegionRepository regionRepository;

    @PreAuthorize(value = "hasAuthority('ADD')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addRegion(@RequestBody Region regionDto){
        ApiResponse apiResponse = regionService.addRegion(regionDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> editRegion(@RequestBody Region regionDto, @PathVariable Integer id) {
        ApiResponse apiResponse = regionService.editRegion(regionDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('READ_ALL')")
    @GetMapping("/get")
    public List<Region> getRegionList(){
        return regionRepository.findAll();
    }

    @PreAuthorize(value = "hasAuthority('GET_ONE')")
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getRegionById(@PathVariable Integer id){
        ApiResponse apiResponse = regionService.getRegionById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteRegion(@PathVariable Integer id) {
        ApiResponse apiResponse = regionService.deleteById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}

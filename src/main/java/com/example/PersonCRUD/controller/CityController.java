package com.example.PersonCRUD.controller;

import com.example.PersonCRUD.Dto.CityDto;
import com.example.PersonCRUD.entity.City;
import com.example.PersonCRUD.entity.apiResponse.ApiResponse;
import com.example.PersonCRUD.repository.CityRepository;
import com.example.PersonCRUD.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    CityService cityService;

    @Autowired
    CityRepository cityRepository;

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCity(@RequestBody CityDto cityDto){
        ApiResponse apiResponse = cityService.addCity(cityDto);

        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> edit(@RequestBody CityDto cityDto, @PathVariable Integer id){
        ApiResponse apiResponse = cityService.editCity(cityDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping("/get")
    public List<City> getCityList(){
        return cityRepository.findAll();
    }

    @PreAuthorize(value = "hasAuthority('GET_ONE_PRODUCT')")
    @GetMapping("/get/{id}")
    public ResponseEntity getCityById(@PathVariable Integer id){
        ApiResponse apiResponse = cityService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCity(@PathVariable Integer id){
        ApiResponse apiResponse = cityService.deleteCity(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404 ).body(apiResponse);
    }



}













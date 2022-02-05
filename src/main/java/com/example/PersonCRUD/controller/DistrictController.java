package com.example.PersonCRUD.controller;

import com.example.PersonCRUD.Dto.DistrictDto;
import com.example.PersonCRUD.entity.District;
import com.example.PersonCRUD.entity.apiResponse.ApiResponse;
import com.example.PersonCRUD.repository.DistrictRepository;
import com.example.PersonCRUD.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/district")
public class DistrictController {

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    DistrictService districtService;

    @PreAuthorize(value = "hasAuthority('ADD')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addDistrict(@RequestBody DistrictDto districtDto) {
        ApiResponse apiResponse = districtService.addDistrict(districtDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> editDistrict(@RequestBody DistrictDto districtDto, @PathVariable Integer id) {
        ApiResponse apiResponse = districtService.editDistrict(districtDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('READ_ALL')")
    @GetMapping("/get")
    public List<District> getDistrictList() {
        return districtRepository.findAll();
    }

    @PreAuthorize(value = "hasAuthority('GET_ONE')")
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getDistrict(@PathVariable Integer id) {
        ApiResponse district = districtService.getDistrict(id);
        return ResponseEntity.ok(district);
    }

    @PreAuthorize(value = "hasAuthority('DELETE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteDistrict(@PathVariable Integer id) {
        ApiResponse apiResponse = districtService.deleteDistrict(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}

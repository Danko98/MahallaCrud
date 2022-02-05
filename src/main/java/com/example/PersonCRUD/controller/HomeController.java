package com.example.PersonCRUD.controller;
import com.example.PersonCRUD.Dto.HomeDto;
import com.example.PersonCRUD.entity.Home;
import com.example.PersonCRUD.entity.apiResponse.ApiResponse;
import com.example.PersonCRUD.repository.HomeRepository;
import com.example.PersonCRUD.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    HomeService homeService;

    @Autowired
    HomeRepository homeRepository;

    @PreAuthorize(value = "hasAuthority('ADD')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addHome(@RequestBody HomeDto homeDto) {
        ApiResponse apiResponse = homeService.addHome(homeDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> editHome(@RequestBody HomeDto homeDto, @PathVariable Integer id) {
        ApiResponse apiResponse = homeService.editHome(homeDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('READ_ALL')")
    @GetMapping("/get")
    public List<Home> getHomeList() {
        return homeRepository.findAll();
    }

    @PreAuthorize(value = "hasAuthority('GET_ONE')")
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getHomeById(@PathVariable Integer id) {
        ApiResponse home = homeService.getHome(id);
        return ResponseEntity.ok(home);
    }

    @PreAuthorize(value = "hasAuthority('DELETE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteHome(@PathVariable Integer id) {
        ApiResponse apiResponse = homeService.deleteHome(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}

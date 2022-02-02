package com.example.PersonCRUD.service;

import com.example.PersonCRUD.Dto.HomeDto;
import com.example.PersonCRUD.entity.City;
import com.example.PersonCRUD.entity.Home;
import com.example.PersonCRUD.entity.apiResponse.ApiResponse;
import com.example.PersonCRUD.repository.CityRepository;
import com.example.PersonCRUD.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeService {
    @Autowired
    HomeRepository homeRepository;

    @Autowired
    CityRepository cityRepository;

    public ApiResponse addHome(HomeDto homeDto) {
        if (homeRepository.existsByName(homeDto.getName())) {
            return new ApiResponse("bu uy ro'yxatda mavjud",false);
        }

        if (!cityRepository.existsById(homeDto.getCityId())) {
            return new ApiResponse("Bunday shahar topilamdi", false);
        }

        Optional<City> optionalCity = cityRepository.findById(homeDto.getCityId());

        Home home = new Home();
        home.setName(homeDto.getName());
        home.setCity(optionalCity.get());
        homeRepository.save(home);
            return new ApiResponse("Uy saqlandi",true);
    }

    public ApiResponse editHome(HomeDto homeDto, Integer id) {
        if (!homeRepository.existsById(id)) {
            return new ApiResponse("Bunday uy topilmadi",false);
        }

        if (homeRepository.existsByName(homeDto.getName())) {
            return new ApiResponse("bu uy ro'yxatda mavjud",false);
        }

        if (!cityRepository.existsById(homeDto.getCityId())) {
            return new ApiResponse("Bunday shahar topilamdi", false);
        }

        Optional<Home> optionalHome = homeRepository.findById(id);
        Optional<City> optionalCity = cityRepository.findById(homeDto.getCityId());

        Home home = optionalHome.get();
        home.setName(homeDto.getName());
        home.setCity(optionalCity.get());
        homeRepository.save(home);
            return new ApiResponse("Uy tahrirlandi.",true);
    }

    public ApiResponse getHome(Integer id) {
        if (!homeRepository.existsById(id)) {
            return new ApiResponse("Bunday uy topilmadi",false);
        }
        Optional<Home> optionalHome = homeRepository.findById(id);
        return new ApiResponse(optionalHome.get());
    }

    public ApiResponse deleteHome(Integer id) {
        if (!homeRepository.existsById(id)) {
            return new ApiResponse("Bunday uy topilmadi", false);
        }
        homeRepository.deleteById(id);
        return new ApiResponse("Uy o'chirildi",true);
    }

}

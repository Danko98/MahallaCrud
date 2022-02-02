package com.example.PersonCRUD.service;

import com.example.PersonCRUD.Dto.CityDto;
import com.example.PersonCRUD.entity.City;
import com.example.PersonCRUD.entity.District;
import com.example.PersonCRUD.entity.apiResponse.ApiResponse;
import com.example.PersonCRUD.repository.CityRepository;
import com.example.PersonCRUD.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    DistrictRepository districtRepository;


    public ApiResponse addCity(CityDto cityDto){

        if (cityRepository.existsByNameAndDistrictId(cityDto.getName(),cityDto.getDistrictId())) {
            return new ApiResponse("Bunday shaxar mavjud!!!", false);
        }

        if (!districtRepository.existsById(cityDto.getDistrictId())){
            return new ApiResponse("Bunday tuman topilmadi!!!",false);
        }

        Optional<District> optionalDistrict = districtRepository.findById(cityDto.getDistrictId());
        City city = new City();
        city.setName(cityDto.getName());
        city.setDistrict(optionalDistrict.get());
        cityRepository.save(city);
        return new ApiResponse("Shahar saqlandi.",true);

    }

    public ApiResponse editCity(CityDto cityDto, Integer id){

        if (!cityRepository.existsById(id)){
            return new ApiResponse("Bunday shahar topilamdi",false);
        }

        if (cityRepository.existsByNameAndDistrictId(cityDto.getName(),cityDto.getDistrictId())){
            return new ApiResponse("Bunday shahar mavjud!!!", false);
        }

        if (!districtRepository.existsById(cityDto.getDistrictId())){
            return new ApiResponse("Bunday tuman topilmadi!!!",false);
        }

        Optional<District> optionalDistrict = districtRepository.findById(cityDto.getDistrictId());

        Optional<City> optionalCity = cityRepository.findById(id);

        City city = optionalCity.get();
        city.setName(cityDto.getName());
        city.setDistrict(optionalDistrict.get());
        cityRepository.save(city);
        return new ApiResponse("Shahar tahrirlandi.",true);

    }

    public ApiResponse getById(Integer id){
        if (!cityRepository.existsById(id)){
            return new ApiResponse("Bunday shahar topilamdi!!!",false);
        }
        Optional<City> optionalCity = cityRepository.findById(id);
        return new ApiResponse(optionalCity.get());
    }

    public ApiResponse deleteCity(Integer id){
        if (!cityRepository.existsById(id)) {
            return new ApiResponse("Bunday shahar topilamdi,",false);
        }

        cityRepository.deleteById(id);
        return new ApiResponse("Shahar o'chirildi");
    }


}

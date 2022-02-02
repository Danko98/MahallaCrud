package com.example.PersonCRUD.service;

import com.example.PersonCRUD.Dto.DistrictDto;
import com.example.PersonCRUD.entity.District;
import com.example.PersonCRUD.entity.Region;
import com.example.PersonCRUD.entity.apiResponse.ApiResponse;
import com.example.PersonCRUD.repository.DistrictRepository;
import com.example.PersonCRUD.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    RegionRepository regionRepository;


    public ApiResponse addDistrict(DistrictDto districtDto) {
        if (districtRepository.existsByName(districtDto.getName())){
            return new ApiResponse("Bu nomli tuman mavjud",false);
        }

        if (!regionRepository.existsById(districtDto.getRegionId())){
            return new ApiResponse("Bunday viloyat topilmadi.",false);
        }

        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());

        District district = new District();
        district.setName(districtDto.getName());
        district.setRegion(optionalRegion.get());
        districtRepository.save(district);

        return new ApiResponse("Tuman saqlandi",true);
    }

    public ApiResponse editDistrict(DistrictDto districtDto, Integer id){
        if (!districtRepository.existsById(id)) {
            return new ApiResponse("Bunday tuman topilmadi.",false);
        }

        if (districtRepository.existsByName(districtDto.getName())){
            return new ApiResponse("Bu nomli tuman mavjud",false);
        }

        if (!regionRepository.existsById(districtDto.getRegionId())){
            return new ApiResponse("Bunday viloyat topilmadi.",false);
        }

        Optional<District> optionalDistrict = districtRepository.findById(id);
        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());

        District district = optionalDistrict.get();
        district.setName(districtDto.getName());
        district.setRegion(optionalRegion.get());
        districtRepository.save(district);

        return new ApiResponse("Tuman tahrirlandi.",true);
    }

    public ApiResponse deleteDistrict(Integer id) {
        if (!districtRepository.existsById(id)) {
             return new ApiResponse("Bunday tuman topilmadi.",false);
        }
        districtRepository.deleteById(id);
        return new ApiResponse("Tuman o'chirildi",true);
    }

    public ApiResponse getDistrict(Integer id){
        if (!districtRepository.existsById(id)) {
            return new ApiResponse("Bunday tuman topilmadi.",false);
        }
        Optional<District> optionalDistrict = districtRepository.findById(id);
        return new ApiResponse(optionalDistrict.get());
    }

}

package com.example.PersonCRUD.service;

import com.example.PersonCRUD.entity.Region;
import com.example.PersonCRUD.entity.apiResponse.ApiResponse;
import com.example.PersonCRUD.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    RegionRepository regionRepository;


    public ApiResponse addRegion(Region regionDto){

        if (regionRepository.existsByName(regionDto.getName())){
            return new ApiResponse("Bunday viloyat nomi mavjud!!!", false);
        }

        Region region = new Region();
        region.setName(regionDto.getName());
        regionRepository.save(region);
        return new ApiResponse("Viloyat saqlandi",true);

    }




    public ApiResponse editRegion(Region regionDto, Integer id) {
        if (regionRepository.existsByName(regionDto.getName())){
            return new ApiResponse("Bunday viloyat nomi mavjud!!!", false);
        }

        if (!regionRepository.existsById(id)){
            return new ApiResponse("Bunday viloyat topilmadi", false);
        }
        Optional<Region> optionalRegion = regionRepository.findById(id);

        Region region = optionalRegion.get();
        region.setName(regionDto.getName());
        regionRepository.save(region);
        return new ApiResponse("Viloyat tahrirlandi",true);
    }

    public ApiResponse getRegionById(Integer id){
        if (!regionRepository.existsById(id)){
            return new ApiResponse("Bunday viloyat topilmadi.", false);
        }

        Optional<Region> optionalRegion = regionRepository.findById(id);
        Region region = optionalRegion.get();
        return new ApiResponse(region);
    }

    public ApiResponse deleteById(Integer id) {
        if (regionRepository.existsById(id)){
            regionRepository.deleteById(id);
            return new ApiResponse("Viloyat o'chirildi");
        }else
            return new ApiResponse("Bunday viloyat topilmadi",false);
    }

}

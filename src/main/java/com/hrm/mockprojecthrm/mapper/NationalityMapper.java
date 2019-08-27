package com.hrm.mockprojecthrm.mapper;

import com.hrm.mockprojecthrm.dto.NationalityDTO;
import com.hrm.mockprojecthrm.entity.Nationality;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NationalityMapper {

    public List<NationalityDTO> nationalityToNationalityDTOs(List<Nationality> nationalities){
        return nationalities.stream().map(nationality -> {
            NationalityDTO nationalityDTO = new NationalityDTO();
            nationalityDTO.setId(nationality.getId());
            nationalityDTO.setName(nationality.getName());
            return nationalityDTO;
        }).collect(Collectors.toList());
    }


    public NationalityDTO nationalityToNationalityDTO(Nationality nationality){
        NationalityDTO nationalityDTO = new NationalityDTO();
        nationalityDTO.setId(nationality.getId());
        nationalityDTO.setName(nationality.getName());
        return nationalityDTO;
    }
}

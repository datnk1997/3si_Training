package com.hrm.mockprojecthrm.controller;

import com.hrm.mockprojecthrm.dto.NationalityDTO;
import com.hrm.mockprojecthrm.dto.UserDTO;
import com.hrm.mockprojecthrm.entity.Nationality;
import com.hrm.mockprojecthrm.entity.User;
import com.hrm.mockprojecthrm.mapper.NationalityMapper;
import com.hrm.mockprojecthrm.repository.NationalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NationalityController {
    @Autowired
    private NationalityRepository nationalityRepository;

    @Autowired
    private NationalityMapper nationalityMapper;

    @GetMapping("/nationality")
    public List<NationalityDTO> getAllAddresses() {
        List<Nationality> nationalityList = nationalityRepository.findAll();
        return nationalityMapper.nationalityToNationalityDTOs(nationalityList);
    }

    @GetMapping("/nationality/{id}")
    public ResponseEntity<?> getAddress(@PathVariable Long id) {
        Optional<Nationality> optionalNationality = nationalityRepository.findById(id);
        NationalityDTO result = nationalityMapper.nationalityToNationalityDTO(optionalNationality.get());
        return ResponseEntity.ok().body(result);
    }

}

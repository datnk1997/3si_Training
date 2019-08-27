package com.hrm.mockprojecthrm.controller;

import com.hrm.mockprojecthrm.dto.PositionDTO;
import com.hrm.mockprojecthrm.entity.Department;
import com.hrm.mockprojecthrm.mapper.PositionMapper;
import com.hrm.mockprojecthrm.repository.DepartmentRepository;
import com.hrm.mockprojecthrm.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PositionController {
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PositionMapper positionMapper;

    @GetMapping("/position")
    public List<PositionDTO> getAllAddresses() {
        return positionMapper.positionTopositionDTOs(positionRepository.findAll());
    }
}

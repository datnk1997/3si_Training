package com.hrm.mockprojecthrm.controller;

import com.hrm.mockprojecthrm.dto.DepartmentDTO;
import com.hrm.mockprojecthrm.mapper.DepartmentMapper;
import com.hrm.mockprojecthrm.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    @GetMapping("/department")
    public List<DepartmentDTO> getAllAddresses() {
        return departmentMapper.departmentsTodepartmentDTOs(departmentRepository.findAll());
    }
}

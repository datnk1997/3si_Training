package com.hrm.mockprojecthrm.mapper;

import com.hrm.mockprojecthrm.dto.DepartmentDTO;
import com.hrm.mockprojecthrm.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentMapper {

    @Autowired
    private LocationMapper locationMapper;

    public List<DepartmentDTO> departmentsTodepartmentDTOs(List<Department> departments){
        return departments.stream().map(department -> {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setName(department.getName());
            departmentDTO.setLocationDTO(locationMapper.locationToLocationDTO(department.getLocation()));
            return departmentDTO;
        }).collect(Collectors.toList());
    }
}

package com.hrm.mockprojecthrm.mapper;

import com.hrm.mockprojecthrm.dto.LocationDTO;
import com.hrm.mockprojecthrm.entity.Location;
import org.springframework.stereotype.Service;

@Service
public class LocationMapper {
    public LocationDTO locationToLocationDTO(Location location){
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        location.setAddress(location.getAddress());
        return locationDTO;
    }
}

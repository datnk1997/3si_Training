package com.hrm.mockprojecthrm.mapper;

import com.hrm.mockprojecthrm.dto.PositionDTO;
import com.hrm.mockprojecthrm.entity.Position;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionMapper {
    public List<PositionDTO> positionTopositionDTOs(List<Position> positions){
        return positions.stream().map(position -> {
            PositionDTO positionDTO = new PositionDTO();
            positionDTO.setId(position.getId());
            positionDTO.setName(position.getName());
            return positionDTO;
        }).collect(Collectors.toList());
    }
}

package com.hrm.mockprojecthrm.service;

import com.hrm.mockprojecthrm.entity.Leave;
import com.hrm.mockprojecthrm.entity.LeaveType;
import com.hrm.mockprojecthrm.entity.User;
import com.hrm.mockprojecthrm.dto.LeaveDTO;
import com.hrm.mockprojecthrm.repository.LeaveRepository;
import com.hrm.mockprojecthrm.repository.LeaveTypeRepository;
import com.hrm.mockprojecthrm.repository.UserRepository;
import com.hrm.mockprojecthrm.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaveService {
    @Autowired

    private LeaveTypeRepository leaveTypeRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private UserRepository userRepository;

    public List<LeaveType> getAllLeaveType() {
        return (List<LeaveType>) leaveTypeRepository.findAll();
    }

    public void createLeave(LeaveDTO leaveDTO, UserPrincipal userPrincipal) {
        Leave newLeave = new Leave();
        newLeave.setStartDate(leaveDTO.getStartDate());
        newLeave.setEndDate(leaveDTO.getEndDate());
        newLeave.setComment(leaveDTO.getComment());
        newLeave.setIsDelete(leaveDTO.getIsDelete());
        newLeave.setStatus(leaveDTO.getStatus());
        User user = new User();
        user.setId(userPrincipal.getId());
        newLeave.setUser(user);
        LeaveType leaveType = new LeaveType();
        leaveType.setId(leaveDTO.getLeaveTypeId());
        newLeave.setLeaveType(leaveType);
        leaveRepository.save(newLeave);
    }

    public void updateLeave(LeaveDTO leaveDTO) {
        Leave newLeave = new Leave();
        newLeave.setId(leaveDTO.getId());
        newLeave.setStartDate(leaveDTO.getStartDate());
        newLeave.setEndDate(leaveDTO.getEndDate());
        newLeave.setComment(leaveDTO.getComment());
        newLeave.setIsDelete(leaveDTO.getIsDelete());
        newLeave.setStatus(leaveDTO.getStatus());
        Optional<User> user = userRepository.findById(leaveDTO.getUserId());
        newLeave.setUser(user.get());
        LeaveType leaveType = new LeaveType();
        leaveType.setId(leaveDTO.getLeaveTypeId());
        newLeave.setLeaveType(leaveType);
        leaveRepository.save(newLeave);
    }

    public List<LeaveDTO> getLeaveSubmited() {
        List<LeaveDTO> leaveDTOS = new ArrayList<>();
        leaveRepository.findByStatus(2).forEach((leave -> {
            Optional<LeaveType> leaveType = leaveTypeRepository.findById(leave.getLeaveType().getId());
            LeaveDTO leaveDTO = new LeaveDTO();
            leaveDTO.setNameLeaveType(leaveType.get().getName());
            leaveDTO.setId(leave.getId());
            leaveDTO.setLeaveTypeId(leave.getLeaveType().getId());
            Optional<User> user = userRepository.findById(leave.getUser().getId());
            leaveDTO.setName(user.get().getName());
            leaveDTO.setIsDelete(leave.getIsDelete());
            leaveDTO.setEndDate(leave.getEndDate());
            leaveDTO.setStartDate(leave.getStartDate());
            leaveDTO.setStatus(leave.getStatus());
            leaveDTO.setComment(leave.getComment());
            leaveDTO.setCreateAt(leave.getCreatedAt());
            leaveDTO.setCreateBy(leave.getCreatedBy());
            leaveDTO.setUserId(leave.getUser().getId());
            leaveDTOS.add(leaveDTO);
        }));
        List<LeaveDTO> newLeaveDTOs = new ArrayList<>();
        leaveDTOS.forEach(leaveDTO -> {
             if(LocalDate.now().compareTo(leaveDTO.getStartDate())>=0 && LocalDate.now().compareTo(leaveDTO.getEndDate())<=0){
                 newLeaveDTOs.add(leaveDTO);
             }
        });
        return newLeaveDTOs;
    }

    public List<LeaveDTO> getLeaveByStatus(Integer status) {
        List<LeaveDTO> leaveDTOS = new ArrayList<>();
        leaveRepository.findByStatus(status).forEach((leave -> {
            Optional<LeaveType> leaveType = leaveTypeRepository.findById(leave.getLeaveType().getId());
            LeaveDTO leaveDTO = new LeaveDTO();
            leaveDTO.setNameLeaveType(leaveType.get().getName());
            leaveDTO.setId(leave.getId());
            leaveDTO.setLeaveTypeId(leave.getLeaveType().getId());
            Optional<User> user = userRepository.findById(leave.getUser().getId());
            leaveDTO.setName(user.get().getName());
            leaveDTO.setIsDelete(leave.getIsDelete());
            leaveDTO.setEndDate(leave.getEndDate());
            leaveDTO.setStartDate(leave.getStartDate());
            leaveDTO.setStatus(leave.getStatus());
            leaveDTO.setComment(leave.getComment());
            leaveDTO.setCreateAt(leave.getCreatedAt());
            leaveDTO.setCreateBy(leave.getCreatedBy());
            leaveDTO.setUserId(leave.getUser().getId());
            leaveDTOS.add(leaveDTO);
        }));
        return leaveDTOS;

    }
}

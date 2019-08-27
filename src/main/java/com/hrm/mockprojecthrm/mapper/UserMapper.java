package com.hrm.mockprojecthrm.mapper;

import com.hrm.mockprojecthrm.dto.UserDTO;
import com.hrm.mockprojecthrm.dto.UserJobDTO;
import com.hrm.mockprojecthrm.entity.Department;
import com.hrm.mockprojecthrm.entity.Nationality;
import com.hrm.mockprojecthrm.entity.Position;
import com.hrm.mockprojecthrm.entity.User;
import com.hrm.mockprojecthrm.repository.DepartmentRepository;
import com.hrm.mockprojecthrm.repository.NationalityRepository;
import com.hrm.mockprojecthrm.repository.PositionRepository;
import com.hrm.mockprojecthrm.repository.UserRepository;
import com.hrm.mockprojecthrm.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NationalityRepository nationalityRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    public UserDTO userPrincipalMaptoUserSummary(UserPrincipal userPrincipal) {
        UserDTO userSummary = new UserDTO();
        userSummary.setId(userPrincipal.getId());
        userSummary.setName(userPrincipal.getName());
        userSummary.setBirthday(userPrincipal.getBirthday());
        userSummary.setGender(userPrincipal.getGender());
        for (GrantedAuthority auth: userPrincipal.getAuthorities()){
            userSummary.setAuthor(auth.getAuthority());
        }
        if (userPrincipal.getNationality() == null) {
            userSummary.setNationality(0L);
        } else {
            userSummary.setNationality(userPrincipal.getNationality().getId());
        }
        userSummary.setMaritalStatus(userPrincipal.getMaritalStatus());
        return userSummary;
    }

    public UserDTO userMaptoUserSummary(User user) {
        UserDTO userSummary = new UserDTO();
        userSummary.setId(user.getId());
        userSummary.setName(user.getName());
        userSummary.setBirthday(user.getBirthday());
        userSummary.setGender(user.getGender());
        if (user.getNationality() == null) {
            userSummary.setNationality(0L);
        } else {
            userSummary.setNationality(user.getNationality().getId());
        }
        userSummary.setMaritalStatus(user.getMaritalStatus());
        return userSummary;
    }

    public List<UserDTO> userToUserDTOs(List<User> users) {
        return users.stream().map(user -> {
            return this.userMaptoUserSummary(user);
        }).collect(Collectors.toList());
    }

    public User userSummaryMaptoUser(UserDTO userSummary) {
        Optional<Nationality> opNationality = nationalityRepository.findById(userSummary.getNationality());
        Optional<User> opUser = userRepository.findById(userSummary.getId());
        opUser.ifPresent(user -> {
            user.setName(userSummary.getName());
            user.setGender(userSummary.getGender());
            user.setMaritalStatus(userSummary.getMaritalStatus());
            user.setBirthday(userSummary.getBirthday());
            if (opNationality.isPresent()) {
                user.setNationality(opNationality.get());
            } else {
                user.setNationality(null);
            }

        });
        return opUser.get();
    }

    public User userJobDTOMaptoUser(UserJobDTO userJobDTO) {
        Optional<Department> opDep = departmentRepository.findById(userJobDTO.getDepartment());
        Optional<Position> opPo = positionRepository.findById(userJobDTO.getPosition());
        Optional<User> opUser = userRepository.findById(userJobDTO.getId());
        opUser.ifPresent(user -> {
            user.setManager_id(userJobDTO.getManager_id());
            user.setHireDate(userJobDTO.getHireDate());
            if (opDep.isPresent()) {
                user.setDepartment(opDep.get());
            } else {
                user.setDepartment(null);
            }
            if (opPo.isPresent()) {
                user.setPosition(opPo.get());
            } else {
                user.setPosition(null);
            }
        });
        return opUser.get();
    }
}

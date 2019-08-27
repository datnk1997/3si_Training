package com.hrm.mockprojecthrm.service;

import com.hrm.mockprojecthrm.dto.LoginRequest;
import com.hrm.mockprojecthrm.dto.SignUpRequest;
import com.hrm.mockprojecthrm.dto.UserDTO;
import com.hrm.mockprojecthrm.entity.*;
import com.hrm.mockprojecthrm.exception.AppException;
import com.hrm.mockprojecthrm.repository.*;
import com.hrm.mockprojecthrm.security.JwtTokenProvider;
import com.hrm.mockprojecthrm.security.UserPrincipal;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private NationalityRepository nationalityRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public User createUser(SignUpRequest signUpRequest) {
        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));
        user.setNationality(nationalityRepository.getOne(1L));
        user.setIsDelete(false);
        User result = userRepository.save(user);
        return result;
    }

    public String authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return jwt;
    }

    public void deleteLogicUser(Long id) {
        Optional<User> opUser = userRepository.findById(id);
        opUser.ifPresent(user -> user.setIsDelete(true));
    }

    public List<User> userSearch(String search) {

        FullTextEntityManager fullTextEntityManager
                = Search.getFullTextEntityManager(entityManager);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(User.class)
                .get();

        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .wildcard()
                .onField("name")
                .matching("*" + search + "*")
                .createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(query, User.class);
        List<User> results = jpaQuery.getResultList();
        return results;
    }


    public UserDTO getInfoUser(UserPrincipal currentUser) {
        UserDTO userDTO = new UserDTO();
        userRepository.findById(currentUser.getId()).map(user -> {
                    Optional<Position> position = positionRepository.findById(user.getPosition().getId());
                    Optional<Department> department = departmentRepository.findById(user.getDepartment().getId());
                    Optional<User> us = userRepository.findById(user.getManager_id());
                    userDTO.setPositionName(position.get().getName());
                    userDTO.setDepartmentName(department.get().getName());
                    userDTO.setManagerName(us.get().getName());
                    userDTO.setHireDate(user.getHireDate());
                    userDTO.setGender(user.getGender());
                    userDTO.setBirthday(user.getBirthday());
                    userDTO.setName(user.getName());
                    return userDTO;
                }
        );
        return userDTO;
    }

    public List<UserDTO> getUsersByRoleHR() {
        List<UserDTO> users = new ArrayList<>();
        Role role = new Role();
        role.setId(3L);
        role.setName(RoleName.ROLE_USER);
        userRepository.findByRoles(role).stream().forEach(user -> {
            UserDTO us = new UserDTO();
            us.setId(user.getId());
            us.setName(user.getName());
            us.setEmail(user.getEmail());
            us.setHireDate(user.getHireDate());
            Optional<User> nameManager = userRepository.findById(user.getId());
            us.setManagerName(nameManager.get().getName());
            if (user.getNationality()!=null){
                Optional<Nationality> nationality = nationalityRepository.findById(user.getNationality().getId());
                us.setNationalName(nationality.get().getName());
            }
            Optional<Department> department = departmentRepository.findById(user.getDepartment().getId());
            us.setDepartmentName(department.get().getName());
            Optional<Position> position = positionRepository.findById(user.getPosition().getId());
            us.setPositionName(position.get().getName());
            users.add(us);
        });
        return users;
    }

    public List<UserDTO> getUsersByRoleAdmin() {
        List<UserDTO> users = new ArrayList<>();
        Role role1 = new Role();
        role1.setId(2L);
        role1.setName(RoleName.ROLE_USERHR);
        Role role2 = new Role();
        role2.setId(3L);
        role2.setName(RoleName.ROLE_USER);
        userRepository.findAllByRolesOrRoles(role1, role2).stream().forEach(user -> {
            UserDTO us = new UserDTO();
            us.setId(user.getId());
            us.setName(user.getName());
            us.setEmail(user.getEmail());
            us.setHireDate(user.getHireDate());
            Optional<User> nameManager = userRepository.findById(user.getId());
            us.setManagerName(nameManager.get().getName());
            if (user.getNationality()!=null){
                Optional<Nationality> nationality = nationalityRepository.findById(user.getNationality().getId());
                us.setNationalName(nationality.get().getName());
            }
            Optional<Department> department = departmentRepository.findById(user.getDepartment().getId());
            us.setDepartmentName(department.get().getName());
            Optional<Position> position = positionRepository.findById(user.getPosition().getId());
            us.setPositionName(position.get().getName());
            List<String> roleName = new ArrayList<>();
            user.getRoles().stream().forEach(role ->
                    roleName.add(role.getName().name)
            );
            us.setAuthor(roleName.get(0));
            users.add(us);
        });
        return users;
    }
}

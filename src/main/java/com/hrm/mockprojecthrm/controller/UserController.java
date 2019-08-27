package com.hrm.mockprojecthrm.controller;


import com.hrm.mockprojecthrm.dto.SignUpRequest;
import com.hrm.mockprojecthrm.dto.UserDTO;
import com.hrm.mockprojecthrm.dto.UserJobDTO;
import com.hrm.mockprojecthrm.entity.User;
import com.hrm.mockprojecthrm.exception.ResourceNotFoundException;
import com.hrm.mockprojecthrm.mapper.UserMapper;
import com.hrm.mockprojecthrm.payload.ApiResponse;
import com.hrm.mockprojecthrm.payload.UserIdentityAvailability;
import com.hrm.mockprojecthrm.repository.UserRepository;
import com.hrm.mockprojecthrm.security.CurrentUser;
import com.hrm.mockprojecthrm.security.UserPrincipal;
import com.hrm.mockprojecthrm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user")
    @Secured({"ROLE_ADMIN", "ROLE_USERHR"})
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws URISyntaxException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new com.hrm.mockprojecthrm.payload.ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        User newUser = userService.createUser(signUpRequest);
      /*  URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/" + newUser.getId() )
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).body(new com.hrm.mockprojecthrm.payload.ApiResponse(true, "User registered successfully"));*/

        return ResponseEntity.created(new URI("/api/user/" + newUser.getId()))
                .body(newUser);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<User> getAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    @GetMapping("/user/me")
    @Secured({"ROLE_ADMIN", "ROLE_USERHR", "ROLE_USER"})
    public UserDTO getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return userMapper.userPrincipalMaptoUserSummary(currentUser);
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return userRepository.findById(id).map(user -> ResponseEntity.ok().body(user))
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }


    @PutMapping("/user/edit")
    @Secured({"ROLE_ADMIN", "ROLE_USERHR"})
    public ResponseEntity<?> editUser(@Valid @RequestBody UserJobDTO userJobDTO) {
        if (userJobDTO.getId() == null) {
            return new ResponseEntity<>(new ApiResponse(false, "id not null!"),
                    HttpStatus.BAD_REQUEST);
        }
        User user = userMapper.userJobDTOMaptoUser(userJobDTO);
        User result = userRepository.save(user);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/user")
    @Secured({"ROLE_ADMIN", "ROLE_USERHR", "ROLE_USER"})
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userSummary) {
        if (userSummary.getId() == null) {
            return new ResponseEntity<>(new ApiResponse(false, "id not null!"),
                    HttpStatus.BAD_REQUEST);
        }
        User user = userMapper.userSummaryMaptoUser(userSummary);
        user = userRepository.save(user);
        UserDTO result = userMapper.userMaptoUserSummary(user);
        return ResponseEntity.ok().body(result);
    }


    @DeleteMapping("/user/{userId}")
    @Secured({"ROLE_ADMIN", "ROLE_USERHR"})
    public ResponseEntity<?> deletePost(@PathVariable Long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().body(new ApiResponse(true, "User delete successfully"));
        }).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }


    @GetMapping(value = "/users/search")
    public List<UserDTO> fullTextSearch(@RequestParam(value = "searchKey") String searchKey) {
        return userMapper.userToUserDTOs(userService.userSearch(searchKey));
    }

    @GetMapping(value = "/info-user")
    @Secured({"ROLE_ADMIN", "ROLE_USERHR", "ROLE_USER"})
    public UserDTO getInfoUser(@CurrentUser UserPrincipal currentUser) {
        return userService.getInfoUser(currentUser);
    }

    @GetMapping(value = "/hr/users")
    @Secured({"ROLE_USERHR"})
    public List<UserDTO> getUsersByRoleHR(){
        return userService.getUsersByRoleHR();
    }

    @GetMapping(value = "/admin/users")
    @Secured({"ROLE_ADMIN"})
    public List<UserDTO> getUsersByRoleAdmin(){
        return userService.getUsersByRoleAdmin();
    }
}

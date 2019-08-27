package com.hrm.mockprojecthrm.controller;

import com.hrm.mockprojecthrm.entity.User;
import com.hrm.mockprojecthrm.payload.ApiResponse;
import com.hrm.mockprojecthrm.payload.JwtAuthenticationResponse;
import com.hrm.mockprojecthrm.dto.LoginRequest;
import com.hrm.mockprojecthrm.dto.SignUpRequest;
import com.hrm.mockprojecthrm.repository.RoleRepository;
import com.hrm.mockprojecthrm.repository.UserRepository;
import com.hrm.mockprojecthrm.security.CurrentUser;
import com.hrm.mockprojecthrm.security.JwtTokenProvider;
import com.hrm.mockprojecthrm.security.UserPrincipal;
import com.hrm.mockprojecthrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, @CurrentUser UserPrincipal currentUser) {
        String jwt = userService.authenticateUser(loginRequest);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
}

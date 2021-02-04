package com.snitch.api.security.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.snitch.api.repository.RoleRepository;
import com.snitch.api.repository.UserRepository;
import com.snitch.api.security.JwtUtils;
import com.snitch.api.security.request.LoginRequest;
import com.snitch.api.security.request.SignupRequest;
import com.snitch.api.security.response.JwtResponse;
import com.snitch.api.security.response.MessageResponse;
import com.snitch.api.security.service.IAuthService;
import com.snitch.entities.model.Role;
import com.snitch.entities.model.User;

import enums.ERole;
import javassist.NotFoundException;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(token,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) throws NotFoundException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        Role userRole = roleRepository.getByName(ERole.ROLE_USER)
                .orElseThrow(() -> new NotFoundException("Error: Role user not found!"));

        User user = new User(signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getPosition(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                Collections.singleton(userRole));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}

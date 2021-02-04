package com.snitch.api.security.service;

import org.springframework.http.ResponseEntity;

import com.snitch.api.security.request.LoginRequest;
import com.snitch.api.security.request.SignupRequest;

import javassist.NotFoundException;

public interface IAuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    ResponseEntity<?> registerUser(SignupRequest signUpRequest) throws NotFoundException;
}

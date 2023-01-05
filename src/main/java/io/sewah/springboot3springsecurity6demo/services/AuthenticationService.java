package io.sewah.springboot3springsecurity6demo.services;

import io.sewah.springboot3springsecurity6demo.dto.AuthenticationResponse;
import io.sewah.springboot3springsecurity6demo.dto.LoginRequest;
import io.sewah.springboot3springsecurity6demo.dto.SignupRequest;

public interface AuthenticationService {
    AuthenticationResponse signup(SignupRequest request);
    AuthenticationResponse login(LoginRequest request);
}

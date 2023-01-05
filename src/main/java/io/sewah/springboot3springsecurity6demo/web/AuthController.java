package io.sewah.springboot3springsecurity6demo.web;

import io.sewah.springboot3springsecurity6demo.dto.AuthenticationResponse;
import io.sewah.springboot3springsecurity6demo.dto.LoginRequest;
import io.sewah.springboot3springsecurity6demo.dto.SignupRequest;
import io.sewah.springboot3springsecurity6demo.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody SignupRequest signupResponse
    ){
        return ResponseEntity.ok(authenticationService.signup(signupResponse));
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest loginRequest
    ){
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }
}

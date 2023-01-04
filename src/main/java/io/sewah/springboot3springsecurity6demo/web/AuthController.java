package io.sewah.springboot3springsecurity6demo.web;

import io.sewah.springboot3springsecurity6demo.dto.AuthenticationResponse;
import io.sewah.springboot3springsecurity6demo.dto.LoginRequest;
import io.sewah.springboot3springsecurity6demo.dto.SignupRequest;
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

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody SignupRequest signupResponse
    ){
        //
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest loginRequest
    ){
        //
    }
}

package io.sewah.springboot3springsecurity6demo.services.impls;

import io.sewah.springboot3springsecurity6demo.configs.JwtUtils;
import io.sewah.springboot3springsecurity6demo.dto.AuthenticationResponse;
import io.sewah.springboot3springsecurity6demo.dto.LoginRequest;
import io.sewah.springboot3springsecurity6demo.dto.SignupRequest;
import io.sewah.springboot3springsecurity6demo.entities.Role;
import io.sewah.springboot3springsecurity6demo.entities.User;
import io.sewah.springboot3springsecurity6demo.repositories.UserRepository;
import io.sewah.springboot3springsecurity6demo.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
   private final PasswordEncoder passwordEncoder;
   private final UserRepository userRepository;
   private final JwtUtils jwtUtils;
   private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse signup(SignupRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}

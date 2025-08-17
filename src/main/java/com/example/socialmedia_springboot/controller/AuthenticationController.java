package com.example.socialmedia_springboot.controller;

import com.example.socialmedia_springboot.dto.LoginRequestDto;
import com.example.socialmedia_springboot.dto.RegistrationRequestDto;
import com.example.socialmedia_springboot.dto.UserDto;
import com.example.socialmedia_springboot.model.User;
import com.example.socialmedia_springboot.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
//    @GetMapping("/register")
//    public String register(RegistrationRequestDto registrationRequestDto) {
//        return "success";
//    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequestDto registrationRequestDto){
        userService.registerUser(registrationRequestDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request) {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDto userDto = userService.findUserByEmail(request.getEmail());


            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "userId", userDto.getId(),
                    "email", userDto.getEmail()

            ));
    }



}

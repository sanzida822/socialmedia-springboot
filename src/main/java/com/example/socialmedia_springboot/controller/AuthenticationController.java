package com.example.socialmedia_springboot.controller;

import com.example.socialmedia_springboot.dto.RegistrationRequestDto;
import com.example.socialmedia_springboot.model.User;
import com.example.socialmedia_springboot.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final UserService userService;
    @GetMapping("/register")
//    public String register(RegistrationRequestDto registrationRequestDto) {
//        return "success";
//    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegistrationRequestDto registrationRequestDto){
        userService.registerUser(registrationRequestDto);
    }


}

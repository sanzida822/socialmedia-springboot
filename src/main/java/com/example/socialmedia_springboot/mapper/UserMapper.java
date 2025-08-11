package com.example.socialmedia_springboot.mapper;

import com.example.socialmedia_springboot.dto.RegistrationRequestDto;
import com.example.socialmedia_springboot.dto.UserDto;
import com.example.socialmedia_springboot.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
//    public User toEntity(RegistrationRequestDto registrationRequestDto, Image profileImage){
//        return User.builder().username(registrationRequestDto.getUserName())
//                .email(registrationRequestDto.getEmail()).password(PasswordUtil.HashPassword(registrationRequestDto.getPassword()))
//                .profileImage(profileImage).build();
//    }


    public User toEntity(RegistrationRequestDto registrationRequestDto){
        return User.builder()
                .username(registrationRequestDto.getUserName())
                .email(registrationRequestDto.getEmail())
                .password(passwordEncoder.encode(registrationRequestDto.getPassword()))
                .build();
    }
    public UserDto toDto(User user){
        return UserDto.builder().id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .userCreated(user.getCreatedAt())
                .userUpdated(user.getUpdatedAt()).build();
    }
}

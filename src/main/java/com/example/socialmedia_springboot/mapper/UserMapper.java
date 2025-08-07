package com.example.socialmedia_springboot.mapper;

import org.example.socialmediamvc.dto.RegistrationRequestDto;
import org.example.socialmediamvc.dto.UserDto;
import org.example.socialmediamvc.model.User;
import org.example.socialmediamvc.utils.PasswordUtil;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
//    public User toEntity(RegistrationRequestDto registrationRequestDto, Image profileImage){
//        return User.builder().username(registrationRequestDto.getUserName())
//                .email(registrationRequestDto.getEmail()).password(PasswordUtil.HashPassword(registrationRequestDto.getPassword()))
//                .profileImage(profileImage).build();
//    }


    public User toEntity(RegistrationRequestDto registrationRequestDto){
        return User.builder()
                .username(registrationRequestDto.getUserName())
                .email(registrationRequestDto.getEmail())
                .password(PasswordUtil.HashPassword(registrationRequestDto.getPassword()))
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

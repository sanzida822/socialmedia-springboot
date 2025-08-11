package com.example.socialmedia_springboot.service;

import com.example.socialmedia_springboot.dto.RegistrationRequestDto;
import com.example.socialmedia_springboot.dto.UserDto;
import com.example.socialmedia_springboot.exception.UserNotFoundException;
import com.example.socialmedia_springboot.mapper.UserMapper;
import com.example.socialmedia_springboot.model.User;
import com.example.socialmedia_springboot.repository.UserRepository;
import com.example.socialmedia_springboot.utils.Constants;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(Constants.ErrorMessage.USER_NOT_FOUND));
    }

    public UserDto findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(null);
    }


    public UserDto getUserById(long userId) {
        User user= userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(Constants.ErrorMessage.USER_NOT_FOUND));
        return userMapper.toDto(user);
    }

    public void registerUser(RegistrationRequestDto registrationRequestDto) {
        User user=userMapper.toEntity(registrationRequestDto);
        userRepository.save(user);
    }

}

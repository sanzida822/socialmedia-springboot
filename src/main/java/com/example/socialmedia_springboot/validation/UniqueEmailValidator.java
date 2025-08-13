package com.example.socialmedia_springboot.validation;

import com.example.socialmedia_springboot.dto.UserDto;
import com.example.socialmedia_springboot.exception.UserNotFoundException;
import com.example.socialmedia_springboot.repository.UserRepository;
import com.example.socialmedia_springboot.service.UserService;
import com.example.socialmedia_springboot.utils.CommonUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserRepository userRepository;
    public boolean isValid(String email, ConstraintValidatorContext context) {
     if(CommonUtil.isNullOrEmpty(email)) {
         return true;
     }
     return !userRepository.existsByEmail(email);
    }
}

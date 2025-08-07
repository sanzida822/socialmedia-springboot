package com.example.socialmedia_springboot.validation;

import com.example.socialmedia_springboot.dto.UserDto;
import com.example.socialmedia_springboot.utils.CommonUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserService userService;
    public boolean isValid(String email, ConstraintValidatorContext context) {
        UserDto userDto=new UserDto();
        userDto=userService.emailExists(email);
        return CommonUtil.isNullOrEmpty(email) ||userDto==null;
    }
}

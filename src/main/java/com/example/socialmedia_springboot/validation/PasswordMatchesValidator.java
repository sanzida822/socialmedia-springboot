package com.example.socialmedia_springboot.validation;



import com.example.socialmedia_springboot.dto.RegistrationRequestDto;
import com.example.socialmedia_springboot.utils.CommonUtil;
import com.example.socialmedia_springboot.utils.Constants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegistrationRequestDto> {

    public boolean isValid(RegistrationRequestDto registrationRequestDto, ConstraintValidatorContext context) {
        if(CommonUtil.isNullOrEmpty(registrationRequestDto.getPassword()) || CommonUtil.isNullOrEmpty(registrationRequestDto.getConfirmPassword())) {
            return false;
        }
        boolean isMatched= registrationRequestDto.getPassword().equals(registrationRequestDto.getConfirmPassword());
        if(!isMatched) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(Constants.ErrorMessage.PASSWORD_MISMATCH).addPropertyNode("confirmPassword").addConstraintViolation();
        }
        return isMatched;
    }
}
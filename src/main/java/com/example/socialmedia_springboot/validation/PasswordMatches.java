package com.example.socialmedia_springboot.validation;


import com.example.socialmedia_springboot.utils.Constants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface  PasswordMatches {
    String message() default Constants.ErrorMessage.PASSWORD_MISMATCH;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

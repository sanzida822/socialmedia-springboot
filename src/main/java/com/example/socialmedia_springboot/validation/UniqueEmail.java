package com.example.socialmedia_springboot.validation;


import com.example.socialmedia_springboot.utils.Constants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface  UniqueEmail {
    String message() default Constants.ErrorMessage.EMAIL_ALREADY_REGISTERED;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

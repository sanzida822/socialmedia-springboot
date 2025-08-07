package com.example.socialmedia_springboot.validation;

import com.example.socialmedia_springboot.utils.Constants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowedImageValidator.class)
public @interface AllowedImage {
    String message() default Constants.ErrorMessage.INVALID_IMAGE_TYPE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

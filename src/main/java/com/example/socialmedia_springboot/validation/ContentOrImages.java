package com.example.socialmedia_springboot.validation;

import com.example.socialmedia_springboot.utils.Constants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ContentOrImagesValidator.class)
public @interface ContentOrImages {
    String message() default Constants.ErrorMessage.POST_CONTENT_OR_IMAGE_REQUIRED;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}

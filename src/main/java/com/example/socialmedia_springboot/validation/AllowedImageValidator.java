package com.example.socialmedia_springboot.validation;

import com.example.socialmedia_springboot.utils.Constants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class AllowedImageValidator implements ConstraintValidator<AllowedImage, MultipartFile[]> {
    public boolean isValid(MultipartFile[] imageFiles, ConstraintValidatorContext context) {
        if (imageFiles == null || imageFiles.length == 0) {
            return true;
        }

        for (MultipartFile imageFile : imageFiles) {
            if (imageFile == null || imageFile.isEmpty()) {
                continue;
            }

            String contentType = imageFile.getContentType();
            if (contentType == null || !Constants.Media.ALLOWED_IMAGE_TYPES.contains(contentType)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(Constants.ErrorMessage.INVALID_IMAGE_TYPE).addConstraintViolation();
                return false;
            }
            if (imageFile.getSize() > Constants.Media.MAX_IMAGE_SIZE_BYTES) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(Constants.ErrorMessage.INVALID_IMAGE_SIZE).addConstraintViolation();
                return false;
            }
        }
        return true;
    }

}
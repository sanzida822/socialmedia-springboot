package com.example.socialmedia_springboot.utils;

import java.util.List;

public class Constants {
    public static class ErrorMessage {
        public static final String USERNAME_REQUIRED = "Username is required.";
        public static final String EMAIL_REQUIRED = "Email is required.";
        public static final String INVALID_EMAIL = "Invalid email format.";
        public static final String PASSWORD_REQUIRED = "Password is required.";
        public static final String USERNAME_LENGTH = "Username must be between 5 and 30 characters";
        public static final String EMAIL_ALREADY_REGISTERED = "This email is already registered.";
        public static final String PASSWORD_MISMATCH = "Password doesn't match.";
        public static final String PASSWORD_LENGTH = "Pssword must be between 5 and 30 characters";
        public static final String INVALID_IMAGETYPE = "Only JPEG images are allowed.";
        public static final String INVALID_IMAGESIZE = "Image exceeds 5MB size limit.";
        public static final String INVALID_EMAIL_PASSWORD = "Invalid email or password";
        public static final String NO_SUGGESTIONS_AVAILABLE = "No suggestions available at the moment.";
        public static final String FRIEND_REQUEST_EXISTS = "This user is already registered.";
        public static final String USER_NOT_FOUND = "User not found.";
        public static final String REQUEST_NOT_FOUND = "Friend request not found";
        public static final String POST_CONTENT_OR_IMAGE_REQUIRED="Post must contain either text content or at least one image";
        public static final String POST_CONTENT_LENGTH="Post content cannot exceed 5000 characters.";
        public static final String IMAGE_NOT_FOUND="Image Not Found.";
        public static final String INVALID_IMAGE_TYPE = "Only JPG or PNG images are allowed";
        public static final String INVALID_IMAGE_SIZE = "Image exceeds 5MB size limit.";

    }

    public static class Media{
        public static final List<String> ALLOWED_IMAGE_TYPES = List.of(
                "image/jpeg",
                "image/png"
        );

        public static final long MAX_IMAGE_SIZE_BYTES = 5 * 1024 * 1024;
        public static final List<String> ALLOWED_EXTENSIONS = List.of(".jpg", ".jpeg", ".png");


    }
}

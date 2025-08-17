package com.example.socialmedia_springboot.utils;

import com.example.socialmedia_springboot.dto.UserDto;
import com.example.socialmedia_springboot.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public class CommonUtil {
    private UserService userService;
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    public static boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}

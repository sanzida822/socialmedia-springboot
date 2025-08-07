package com.example.socialmedia_springboot.utils;

import java.util.List;


public class CommonUtil {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    public static boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}

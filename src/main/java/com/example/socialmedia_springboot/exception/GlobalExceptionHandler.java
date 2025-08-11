package com.example.socialmedia_springboot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, Model model) {
        log.error("Exception caught:{}", exception.getMessage(),exception);
        model.addAttribute("errorMessage", exception.getMessage());
        return "error";
    }
}

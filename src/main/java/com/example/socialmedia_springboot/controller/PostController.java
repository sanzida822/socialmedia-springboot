package com.example.socialmedia_springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @GetMapping("/")
    public String index() {
        return "Hello World";
    }
}

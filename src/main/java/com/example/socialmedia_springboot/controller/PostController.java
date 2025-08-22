package com.example.socialmedia_springboot.controller;

import com.example.socialmedia_springboot.dto.PostRequestDto;
import com.example.socialmedia_springboot.dto.UserDto;
import com.example.socialmedia_springboot.model.Post;
import com.example.socialmedia_springboot.service.PostService;
import com.example.socialmedia_springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;
    public String index() {
        return "Hello World";
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Post> save(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        UserDto userDto=userService.findUserByEmail(userDetails.getUsername());

        Post savedPost = postService.save(postRequestDto,userDto);
        return ResponseEntity
                .created(URI.create("/api/v1/posts/" + savedPost.getId()))
                .body(savedPost);
    }
}

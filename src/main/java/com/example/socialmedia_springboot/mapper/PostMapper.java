package com.example.socialmedia_springboot.mapper;

import com.example.socialmedia_springboot.dto.ImageResponseDto;
import com.example.socialmedia_springboot.dto.PostRequestDto;
import com.example.socialmedia_springboot.dto.PostResponseDto;
import com.example.socialmedia_springboot.model.Post;
import com.example.socialmedia_springboot.model.PostImages;
import com.example.socialmedia_springboot.model.User;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostMapper {
    private final UserMapper userMapper;
    private final ImageMapper imageMapper;

    public Post toEntity(PostRequestDto postRequestDto, User user) {
        return Post.builder().content(postRequestDto.getContent())
                .postedBy(user)
                .privacy(postRequestDto.getPrivacy())
                .build();
    }

    public PostResponseDto toDto(Post post, User user) {
        return PostResponseDto.builder().id(post.getId())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .privacy(post.getPrivacy().name())
                .user(userMapper.toDto(user))
                .images(toDto(post.getPostImages()))
                .build();
    }

    public List<ImageResponseDto> toDto(List<PostImages> postImages) {
        return postImages.stream().map(PostImages::getImage)
                .map(imageMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PostResponseDto> toDtoList(List<Post> posts) {
        return posts.stream()
                .map(post -> PostResponseDto.builder()
                        .id(post.getId())
                        .content(post.getContent())
                        .user(userMapper.toDto(post.getPostedBy()))
                        .createdAt(post.getCreatedAt())
                        .images(toDto(post.getPostImages()))
                        .build())
                .collect(Collectors.toList());
    }
}

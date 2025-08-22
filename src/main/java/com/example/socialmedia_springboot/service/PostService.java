package com.example.socialmedia_springboot.service;

import com.example.socialmedia_springboot.dto.PostRequestDto;
import com.example.socialmedia_springboot.dto.UserDto;
import com.example.socialmedia_springboot.mapper.ImageMapper;
import com.example.socialmedia_springboot.mapper.PostMapper;
import com.example.socialmedia_springboot.mapper.UserMapper;
import com.example.socialmedia_springboot.model.Image;
import com.example.socialmedia_springboot.model.Post;
import com.example.socialmedia_springboot.model.PostImages;
import com.example.socialmedia_springboot.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ImageService imageService;
    private final PostMapper postMapper;
    private final UserMapper userMapper;
    private final ImageMapper imageMapper;


    public Post save(PostRequestDto postRequestDto, UserDto userDto) throws IOException {
        Post post = postMapper.toEntity(postRequestDto, userMapper.toEntity(userDto));
        if (postRequestDto.getImages() != null) {
            for (MultipartFile imageFile : postRequestDto.getImages()) {
                if (!imageFile.isEmpty()) {
                    Image image = imageMapper.toEntity(imageFile);
                    PostImages postImages = PostImages.builder().post(post).image(image).build();
                    post.getPostImages().add(postImages);

                }
            }

        }

       return  postRepository.save(post);
    }
}

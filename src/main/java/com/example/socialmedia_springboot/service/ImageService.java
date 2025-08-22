package com.example.socialmedia_springboot.service;

import com.example.socialmedia_springboot.exception.ImageNotFoundException;
import com.example.socialmedia_springboot.mapper.ImageMapper;
import com.example.socialmedia_springboot.model.Image;
import com.example.socialmedia_springboot.repository.ImageRepository;
import com.example.socialmedia_springboot.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    public Image save(MultipartFile imageFile) throws IOException {
        Image image = imageMapper.toEntity(imageFile);
        return imageRepository.save(image);
    }
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(()->new ImageNotFoundException(Constants.ErrorMessage.IMAGE_NOT_FOUND));
    }


}

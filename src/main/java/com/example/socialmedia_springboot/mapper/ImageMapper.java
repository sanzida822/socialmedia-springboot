package com.example.socialmedia_springboot.mapper;
import com.example.socialmedia_springboot.dto.ImageResponseDto;
import com.example.socialmedia_springboot.model.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Component
public class ImageMapper {


    public Image toEntity(MultipartFile imageFile) throws IOException {
        return Image.builder().data(imageFile.getBytes())
                .sizeBytes(imageFile.getSize())
                .filename(imageFile.getOriginalFilename())
                .contentType(imageFile.getContentType())
                .build();

    }

    public ImageResponseDto toDto(Image image) {
        String base64Image = Base64.getEncoder().encodeToString(image.getData());
        return ImageResponseDto.builder()
                .id(image.getId())
                .contentType(image.getContentType())
                .fileName(image.getFilename())
                .build();


    }


}
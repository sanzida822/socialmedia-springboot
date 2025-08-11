package com.example.socialmedia_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ImageResponseDto {
    private Long id;
    private String fileName;
    private String contentType;
//    private String base64Data;

}

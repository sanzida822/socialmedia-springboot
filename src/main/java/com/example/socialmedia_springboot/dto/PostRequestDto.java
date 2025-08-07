package com.example.socialmedia_springboot.dto;

import com.example.socialmedia_springboot.enums.Privacy;
import com.example.socialmedia_springboot.utils.Constants;
import com.example.socialmedia_springboot.validation.AllowedImage;
import com.example.socialmedia_springboot.validation.ContentOrImages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ContentOrImages

public class PostRequestDto {
    private Integer id;

    @NotNull()
    private Privacy privacy;

    @Size(max = 255, message = Constants.ErrorMessage.POST_CONTENT_LENGTH)
    private String content;

    @AllowedImage
    private MultipartFile[] images;


}

package com.example.socialmedia_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    int profileImageId;
    private LocalDateTime userCreated;
    private LocalDateTime userUpdated;
}

package com.example.socialmedia_springboot.dto;

import com.example.socialmedia_springboot.enums.FriendRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequestDto {
    Long id;
    private UserDto sender;
    private UserDto receiver;
    private FriendRequestStatus friendRequestStatus;
    private LocalDateTime friendRequestSent;

}

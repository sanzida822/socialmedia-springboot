package com.example.socialmedia_springboot.mapper;

import com.example.socialmedia_springboot.dto.FriendRequestDto;
import com.example.socialmedia_springboot.model.FriendRequest;
import com.example.socialmedia_springboot.model.User;
import com.example.socialmedia_springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FriendRequestMapper {
private final UserRepository userRepository;
private final UserMapper userMapper;

    public FriendRequest toEntity(FriendRequestDto friendRequestDto, User sender, User receiver) {
        return FriendRequest.builder().sender(sender)
                .receiver(receiver)
                .friendRequestStatus(friendRequestDto.getFriendRequestStatus()).build();

    }

    public FriendRequestDto toDto(FriendRequest friendRequest) {
        return FriendRequestDto.builder()
                .id(friendRequest.getId())
                .sender(userMapper.toDto(friendRequest.getSender()))
                .receiver(userMapper.toDto(friendRequest.getReceiver()))
                .friendRequestSent(friendRequest.getCreatedAt()).build();
    }


}

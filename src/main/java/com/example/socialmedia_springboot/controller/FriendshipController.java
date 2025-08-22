package com.example.socialmedia_springboot.controller;

import com.example.socialmedia_springboot.dto.UserDto;
import com.example.socialmedia_springboot.mapper.UserMapper;
import com.example.socialmedia_springboot.model.User;
import com.example.socialmedia_springboot.service.FriendshipService;
import com.example.socialmedia_springboot.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/friends")
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;
    private final UserService userService;
    private final UserMapper userMapper;


    public List<UserDto> getFriends(@AuthenticationPrincipal UserDetails userDetails) {
        UserDto userDto= userService.findUserByEmail(userDetails.getUsername());
        return friendshipService.getFriends(userDto)
                .stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @PostMapping("/{requestId}/block")
    public void blockFriend(@PathVariable Long requestId) {
        friendshipService.blockFriendship(requestId );
    }

    @PostMapping("/{requestId}/unblock")
    public void unblockFriend(@PathVariable Long requestId) {
        friendshipService.unblockFriendship(requestId);
    }

    @GetMapping("/blocked")
    public List<UserDto> getBlockedUsers(@AuthenticationPrincipal UserDetails userDetails) {
        UserDto userDto= userService.findUserByEmail(userDetails.getUsername());
        return friendshipService.getBlockedFriends(userDto)
                .stream().map(userMapper::toDto)
                .collect(Collectors.toList());

    }


}

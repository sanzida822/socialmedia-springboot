package com.example.socialmedia_springboot.controller;

import com.example.socialmedia_springboot.dto.FriendRequestDto;
import com.example.socialmedia_springboot.dto.FriendRequestViewDto;
import com.example.socialmedia_springboot.dto.UserDto;
import com.example.socialmedia_springboot.enums.FriendRequestStatus;
import com.example.socialmedia_springboot.service.FriendRequestService;
import com.example.socialmedia_springboot.service.FriendshipService;
import com.example.socialmedia_springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/friend-request")
@RequiredArgsConstructor
public class FriendRequestController {
    private final FriendRequestService friendRequestService;
    private final UserService userService;

    @PostMapping("/send/{receiver_id}")
    public ResponseEntity<FriendRequestDto> addFriendRequest(@PathVariable Long receiver_id, @AuthenticationPrincipal UserDetails userDetails) {
        UserDto receiver = userService.getUserById(receiver_id);
        UserDto sender = userService.findUserByEmail(userDetails.getUsername());
        FriendRequestDto friendRequestDto = FriendRequestDto.builder().sender(sender).receiver(receiver).friendRequestStatus(FriendRequestStatus.PENDING).build();
        FriendRequestDto savedRequest = friendRequestService.sendRequest(friendRequestDto);
        URI location = URI.create("/api/v1/friend-request/" + savedRequest.getId());
        return ResponseEntity.created(location).body(savedRequest);
    }

    @GetMapping("/view-request/")
    public ResponseEntity<FriendRequestViewDto> getFriendRequest(@AuthenticationPrincipal UserDetails userDetails) {
        UserDto sender = userService.findUserByEmail(userDetails.getUsername());
        FriendRequestViewDto requests = friendRequestService.getAllRequests(sender.getId());
        return ResponseEntity.ok(requests);
    }

    @PostMapping("/cancel-request/{requestId}")
    public void cancelRequest(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long requestId) {
        UserDto sender = userService.findUserByEmail(userDetails.getUsername());
        friendRequestService.cancelFriendRequest(requestId);

    }

}

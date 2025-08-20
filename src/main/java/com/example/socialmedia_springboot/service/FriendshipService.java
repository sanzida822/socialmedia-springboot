package com.example.socialmedia_springboot.service;

import com.example.socialmedia_springboot.dto.UserDto;
import com.example.socialmedia_springboot.enums.FriendshipStatus;
import com.example.socialmedia_springboot.exception.FriendRequestException;
import com.example.socialmedia_springboot.mapper.UserMapper;
import com.example.socialmedia_springboot.model.FriendRequest;
import com.example.socialmedia_springboot.model.Friendship;
import com.example.socialmedia_springboot.model.User;
import com.example.socialmedia_springboot.repository.FriendshipRepository;
import com.example.socialmedia_springboot.utils.CommonUtil;
import com.example.socialmedia_springboot.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final UserService userService;
    private final FriendRequestService friendRequestService;
    private final UserMapper userMapper;
    List<FriendshipService> friends= new ArrayList<>();

    public List<User> getFriends(UserDto userDto) {
        List<Friendship> sentFriendships  = friendshipRepository.findBySenderAndStatus(userMapper.toEntity(userDto), FriendshipStatus.ACTIVE);
        List<Friendship> receivedFriendships = friendshipRepository.findByReceiverAndStatus(userMapper.toEntity(userDto), FriendshipStatus.ACTIVE);
        List<User> friends = new ArrayList<>();
        for (Friendship friendship : sentFriendships) {
            friends.add(friendship.getReceiver());
        }
        for (Friendship friendship : receivedFriendships) {
            friends.add(friendship.getSender());
        }
        return friends;
    }

    public List<User> getBlockedFriends(UserDto userDto) {
        List<User> blockedFriends = new ArrayList<>();
        List<Friendship> sentBlocked= friendshipRepository.findBySenderAndStatus(userMapper.toEntity(userDto), FriendshipStatus.BLOCK);
        List<Friendship> receiveBlocked=friendshipRepository.findByReceiverAndStatus(userMapper.toEntity(userDto), FriendshipStatus.BLOCK);
        for (Friendship friendship : sentBlocked) {
            blockedFriends.add(friendship.getReceiver());
        }
        for (Friendship friendship : receiveBlocked) { //logged in user received blocked
            blockedFriends.add(friendship.getSender());
        }
        return blockedFriends;
    }

    public void blockFriendship(Long requesterId) {
        FriendRequest friendRequest=friendRequestService.findById(requesterId);
        Friendship friendship = friendshipRepository.findBySenderAndReceiver(friendRequest.getSender(),friendRequest.getReceiver())
                .orElseThrow(()-> new FriendRequestException(Constants.ErrorMessage.FRIEND_REQUEST_NOT_FOUND));
        friendship.setFriendshipStatus(FriendshipStatus.BLOCK);
        friendshipRepository.save(friendship);
    }

    public void unblockFriendship(Long requesterId) {
        FriendRequest friendRequest=friendRequestService.findById(requesterId);
        Friendship friendship = friendshipRepository.findBySenderAndReceiver(friendRequest.getSender(),friendRequest.getReceiver())
                .orElseThrow(()-> new FriendRequestException(Constants.ErrorMessage.FRIEND_REQUEST_NOT_FOUND));
        friendship.setFriendshipStatus(FriendshipStatus.ACTIVE);
        friendshipRepository.save(friendship);
    }




}

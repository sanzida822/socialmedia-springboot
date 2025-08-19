package com.example.socialmedia_springboot.service;

import com.example.socialmedia_springboot.enums.FriendshipStatus;
import com.example.socialmedia_springboot.model.Friendship;
import com.example.socialmedia_springboot.model.User;
import com.example.socialmedia_springboot.repository.FriendshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    List<FriendshipService> friends= new ArrayList<>();

    public List<User> getFriends(User user) {
        List<Friendship> sentFriendships  = friendshipRepository.findSenderAndStatus(user, FriendshipStatus.ACTIVE);
        List<Friendship> receivedFriendships = friendshipRepository.findReceiverAndStatus(user, FriendshipStatus.ACTIVE);
        List<User> friends = new ArrayList<>();
        for (Friendship friendship : sentFriendships) {
            friends.add(friendship.getReceiver());
        }
        for (Friendship friendship : receivedFriendships) {
            friends.add(friendship.getSender());
        }
        return friends;
    }

    public void blockFriendship(User user, Long requesterId) {


    }




}

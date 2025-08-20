package com.example.socialmedia_springboot.repository;


import com.example.socialmedia_springboot.enums.FriendshipStatus;
import com.example.socialmedia_springboot.model.Friendship;
import com.example.socialmedia_springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    Optional<Friendship> findBySenderAndReceiver(User sender, User receiver);

    List<Friendship> findBySenderAndStatus(User user, FriendshipStatus status);

    List<Friendship> findByReceiverAndStatus(User user, FriendshipStatus status);

    List<Friendship> blockedFriendships(User user, FriendshipStatus status);





}

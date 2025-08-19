package com.example.socialmedia_springboot.repository;

import com.example.socialmedia_springboot.enums.FriendshipStatus;
import com.example.socialmedia_springboot.model.Friendship;
import com.example.socialmedia_springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findSenderAndStatus(User sender, FriendshipStatus status);
    List<Friendship> findReceiverAndStatus(User receiver, FriendshipStatus status);

    Optional<Friendship> findBySenderAndReceiver(User sender, User receiver);
    




}

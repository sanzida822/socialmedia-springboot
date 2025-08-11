package com.example.socialmedia_springboot.repository;

import com.example.socialmedia_springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
            "u.id != :userId AND " +
            "NOT EXISTS (SELECT 1 FROM FriendRequest fr WHERE " +
            "   (fr.sender.id = :userId AND fr.receiver.id = u.id) OR " +
            "   (fr.sender.id = u.id AND fr.receiver.id = :userId)) AND " +
            "NOT EXISTS (SELECT 1 FROM Friendship f WHERE " +
            "   (f.sender.id = :userId AND f.receiver.id = u.id) OR " +
            "   (f.sender.id = u.id AND f.receiver.id = :userId))")
    List<User> findNonFriendUsers(@Param("userId") int userId);
}

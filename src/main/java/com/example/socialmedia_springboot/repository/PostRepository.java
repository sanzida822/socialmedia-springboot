package com.example.socialmedia_springboot.repository;

import com.example.socialmedia_springboot.model.Post;
import com.example.socialmedia_springboot.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"postedBy", "postImages","postImages.image"})
    @Query("SELECT DISTINCT p FROM Post p WHERE " +
            "(p.privacy = 'PUBLIC') OR " +
            "(p.privacy = 'FRIENDS' AND EXISTS (" +
            "  SELECT 1 FROM Friendship f WHERE " +
            "  ((f.sender = :user AND f.receiver = p.postedBy) OR " +
            "   (f.receiver = :user AND f.sender = p.postedBy)) AND " +
            "  f.friendshipStatus = 'ACTIVE'" +
            ")) OR " +
            "(p.postedBy = :user)")
    List<Post> findVisiblePosts(@Param("user") User user);

}

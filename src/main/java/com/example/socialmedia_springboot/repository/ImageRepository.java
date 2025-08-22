package com.example.socialmedia_springboot.repository;

import com.example.socialmedia_springboot.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}

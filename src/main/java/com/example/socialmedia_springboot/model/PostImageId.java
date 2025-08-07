package com.example.socialmedia_springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PostImageId implements Serializable {

    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "image_id")
    private Integer imageId;
}
package com.example.socialmedia_springboot.exception;

public class FriendRequestException extends RuntimeException {
    public FriendRequestException(String message) {
        super(message);
    }
}

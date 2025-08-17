package com.example.socialmedia_springboot.service;
import com.example.socialmedia_springboot.dto.FriendRequestDto;
import com.example.socialmedia_springboot.dto.FriendRequestViewDto;
import com.example.socialmedia_springboot.exception.FriendRequestException;
import com.example.socialmedia_springboot.exception.UserNotFoundException;
import com.example.socialmedia_springboot.mapper.FriendRequestMapper;
import com.example.socialmedia_springboot.model.FriendRequest;
import com.example.socialmedia_springboot.model.User;
import com.example.socialmedia_springboot.repository.FriendRequestRepository;
import com.example.socialmedia_springboot.repository.UserRepository;
import com.example.socialmedia_springboot.utils.Constants;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final FriendRequestMapper friendRequestMapper;

    public FriendRequestDto sendRequest (FriendRequestDto friendRequestDto) {
        User sender=userService.findUserById(friendRequestDto.getSender().getId());
        User receiver=userService.findUserById(friendRequestDto.getReceiver().getId());
        if(friendRequestRepository.existsBySenderAndReceiver(sender, receiver)) {
            throw new FriendRequestException(Constants.ErrorMessage.FRIEND_REQUEST_EXISTS);
        }
        FriendRequest friendRequest=friendRequestMapper.toEntity(friendRequestDto,sender,receiver);
        FriendRequest saved=friendRequestRepository.save(friendRequest);
        return  friendRequestMapper.toDto(saved);
    }

    public FriendRequest findById(Long requestId) {
        return friendRequestRepository.findById(requestId).orElseThrow(()->new EntityNotFoundException(Constants.ErrorMessage.REQUEST_NOT_FOUND));
    }

    public void deleteById(Long requestId) {
        friendRequestRepository.deleteById(requestId);
    }

    public FriendRequestViewDto getAllRequests(Long userId) {
        return FriendRequestViewDto.builder().receivedRequests(getReceivedRequest(userId))
                .sentRequests(getSentFriendRequests(userId)).build();
    }

    public List<FriendRequestDto> getReceivedRequest(Long receiverId) {
        List<FriendRequest> receivedRequests=friendRequestRepository.findReceivedRequests(receiverId);
        return receivedRequests.stream().map(friendRequestMapper::toDto).collect(Collectors.toList());
    }

    public List<FriendRequestDto> getSentFriendRequests(Long senderId) {
        List<FriendRequest> sentRequest=friendRequestRepository.findSentRequests(senderId);
        return sentRequest.stream().map(friendRequestMapper::toDto).collect(Collectors.toList());
    }

    public void cancelFriendRequest(Long requestId) {
        friendRequestRepository.deleteById(requestId);
    }


}

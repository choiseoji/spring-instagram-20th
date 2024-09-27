package com.ceos20.instagram.chatRoom.service;

import com.ceos20.instagram.chatRoom.domain.ChatRoom;
import com.ceos20.instagram.chatRoom.repository.ChatRoomRepository;
import com.ceos20.instagram.user.domain.User;
import com.ceos20.instagram.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    // 채팅방 생성
    public void createChatRoom(Long userId, Long friendId) {

        // 나 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 입니다."));

        // 상대방 조회
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 입니다."));

        ChatRoom chatRoom = ChatRoom.builder()
                .user1(user)
                .user2(friend)
                .build();
        chatRoomRepository.save(chatRoom);
    }
}

package com.ceos20.instagram.chatRoom.service;

import com.ceos20.instagram.chatRoom.domain.ChatRoom;
import com.ceos20.instagram.chatRoom.repository.ChatRoomRepository;
import com.ceos20.instagram.member.domain.Member;
import com.ceos20.instagram.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {

    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;

    // 채팅방 생성
    @Transactional
    public void createChatRoom(Long memberId, Long friendId) {

        // 나 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 입니다."));

        // 상대방 조회
        Member friend = memberRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 입니다."));

        ChatRoom chatRoom = ChatRoom.toEntity(member, friend);
        chatRoomRepository.save(chatRoom);
    }
}

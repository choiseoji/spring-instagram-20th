package com.ceos20.instagram.message.dto;

import com.ceos20.instagram.chatRoom.domain.ChatRoom;
import com.ceos20.instagram.message.domain.Message;
import com.ceos20.instagram.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageRequest {

    private String content;

    private Long roomId;

    public Message toEntity(Member sender, ChatRoom chatRoom) {
        return Message.builder()
                .content(content)
                .sender(sender)
                .chatRoom(chatRoom)
                .build();
    }
}

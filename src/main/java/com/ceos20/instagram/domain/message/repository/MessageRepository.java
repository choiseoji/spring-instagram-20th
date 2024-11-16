package com.ceos20.instagram.domain.message.repository;

import com.ceos20.instagram.domain.chatRoom.domain.ChatRoom;
import com.ceos20.instagram.domain.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByChatRoom(ChatRoom chatRoom);
}

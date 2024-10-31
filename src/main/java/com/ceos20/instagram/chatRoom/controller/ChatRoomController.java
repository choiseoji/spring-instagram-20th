package com.ceos20.instagram.chatRoom.controller;

import com.ceos20.instagram.chatRoom.dto.GetChatRoomIdResponse;
import com.ceos20.instagram.chatRoom.service.ChatRoomService;
import com.ceos20.instagram.global.annotation.Login;
import com.ceos20.instagram.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/friend/{friendId}")
    public ResponseEntity createChatRoom(
            @PathVariable Long friendId,
            @Login Member member) {

        chatRoomService.createChatRoom(member.getId(), friendId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GetChatRoomIdResponse>> getMyChatRoomIds(@Login Member member) {

        List<GetChatRoomIdResponse> responses = chatRoomService.getMyChatRoomIds(member);
        return ResponseEntity.ok(responses);
    }
}

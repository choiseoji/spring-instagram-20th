package com.ceos20.instagram.message.controller;

import com.ceos20.instagram.common.annotation.Login;
import com.ceos20.instagram.member.domain.Member;
import com.ceos20.instagram.message.dto.CreateMessageRequest;
import com.ceos20.instagram.message.dto.GetMessageResponse;
import com.ceos20.instagram.message.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity createMessage(
            @RequestBody CreateMessageRequest createMessageRequest,
            @Login Member member) {

        messageService.createMessage(createMessageRequest, member);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<List<GetMessageResponse>> getMessageInChatRoom(@PathVariable Long chatRoomId) {

        List<GetMessageResponse> responses = messageService.getMessageInChatRoom(chatRoomId);
        return ResponseEntity.ok(responses);
    }
}

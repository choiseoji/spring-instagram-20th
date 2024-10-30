package com.ceos20.instagram.postLike.controller;

import com.ceos20.instagram.global.annotation.Login;
import com.ceos20.instagram.member.domain.Member;
import com.ceos20.instagram.postLike.dto.GetUserLikeResponse;
import com.ceos20.instagram.postLike.service.PostLikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/postlike")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity handlePostLike(
            @PathVariable Long postId,
            @Login Member member
            ) {

        postLikeService.handlePostLike(postId, member.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/member/{postId}")
    public ResponseEntity<List<GetUserLikeResponse>> getUsersWhoLikePost(@PathVariable Long postId) {

        List<GetUserLikeResponse> responses = postLikeService.getUsersWhoLikePost(postId);
        return ResponseEntity.ok(responses);
    }
}

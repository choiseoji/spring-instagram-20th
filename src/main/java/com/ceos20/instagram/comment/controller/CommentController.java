package com.ceos20.instagram.comment.controller;

import com.ceos20.instagram.comment.dto.CreateCommentRequest;
import com.ceos20.instagram.comment.dto.GetCommentResponse;
import com.ceos20.instagram.comment.dto.UpdateCommentRequest;
import com.ceos20.instagram.comment.service.CommentService;
import com.ceos20.instagram.global.annotation.Login;
import com.ceos20.instagram.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}")
    public ResponseEntity createComment(
            @RequestBody CreateCommentRequest createCommentRequest,
            @PathVariable Long postId,
            @Login Member member) {

        commentService.createComment(createCommentRequest, member, postId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{commentId}/reply")
    public ResponseEntity createChileComment(
            @RequestBody CreateCommentRequest createCommentRequest,
            @PathVariable Long commentId,
            @Login Member member) {

        commentService.createChildComment(createCommentRequest, member, commentId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity updateContentById(
            @RequestBody UpdateCommentRequest updateCommentRequest,
            @PathVariable Long commentId) {

        commentService.updateContent(updateCommentRequest, commentId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteCommentById(@PathVariable Long commentId) {

        commentService.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<GetCommentResponse>> getAllCommentsByPostId(@PathVariable Long postId) {

        List<GetCommentResponse> responses = commentService.getAllCommentsByPostId(postId);
        return ResponseEntity.ok(responses);
    }
}

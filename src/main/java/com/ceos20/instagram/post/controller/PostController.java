package com.ceos20.instagram.post.controller;

import com.ceos20.instagram.common.annotation.Login;
import com.ceos20.instagram.member.domain.Member;
import com.ceos20.instagram.post.dto.CreatePostRequest;
import com.ceos20.instagram.post.dto.GetPostResponse;
import com.ceos20.instagram.post.dto.UpdatePostContentRequest;
import com.ceos20.instagram.post.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity createPost(
            @RequestBody CreatePostRequest createPostRequest,
            @Login Member member) {

        postService.createPost(createPostRequest, member);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GetPostResponse>> getAllPosts() {

        List<GetPostResponse> responses = postService.getAllPosts();
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity updatePostContentById(
            @PathVariable Long postId,
            @RequestBody UpdatePostContentRequest updatePostContentRequest
            ) {
        postService.updatePostContent(updatePostContentRequest, postId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deletePostById(@PathVariable Long postId) {

        postService.deletePost(postId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<GetPostResponse> getPostById(@PathVariable Long postId) {

        GetPostResponse response = postService.getPostById(postId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<GetPostResponse>> getAllPostsByMemberId(@PathVariable Long memberId) {

        List<GetPostResponse> responses = postService.getAllPostsByMemberId(memberId);
        return ResponseEntity.ok(responses);
    }
}

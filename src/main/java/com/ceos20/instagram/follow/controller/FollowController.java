package com.ceos20.instagram.follow.controller;

import com.ceos20.instagram.follow.dto.GetFollowerResponse;
import com.ceos20.instagram.follow.dto.GetFollowingResponse;
import com.ceos20.instagram.follow.service.FollowService;
import com.ceos20.instagram.global.annotation.Login;
import com.ceos20.instagram.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/friend/{friendId}")
    public ResponseEntity createFollow(
            @PathVariable Long friendId,
            @Login Member member) {

        followService.createFollow(member, friendId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/follower")
    public ResponseEntity<List<GetFollowerResponse>> getFollowers(
            @RequestParam(value = "memberId", required = false) Long memberId,
            @Login Member member) {

        List<GetFollowerResponse> responses = followService.getFollower(memberId, member);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/following")
    public ResponseEntity<List<GetFollowingResponse>> getFollowings(
            @RequestParam(value = "memberId", required = false) Long memberId,
            @Login Member member) {

        List<GetFollowingResponse> responses = followService.getFollowing(memberId, member);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/friend/{friendId}")
    public ResponseEntity deleteFollow(
            @PathVariable Long friendId,
            @Login Member member) {

        followService.deleteFollow(member, friendId);
        return new ResponseEntity(HttpStatus.OK);
    }
}

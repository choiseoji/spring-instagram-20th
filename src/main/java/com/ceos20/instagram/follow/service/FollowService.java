package com.ceos20.instagram.follow.service;

import com.ceos20.instagram.follow.domain.Follow;
import com.ceos20.instagram.follow.dto.GetFollowerResponse;
import com.ceos20.instagram.follow.dto.GetFollowingResponse;
import com.ceos20.instagram.follow.repository.FollowRepository;
import com.ceos20.instagram.user.domain.User;
import com.ceos20.instagram.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    // 팔로우 관계 생성
    @Transactional
    public void createFollow(User user, Long friendId) {

        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));

        Follow follow = Follow.builder()
                .fromUser(user)
                .toUser(friend)
                .build();
        followRepository.save(follow);
    }

    // 나를 팔로우하는 사람들 리스트 반환
    public List<GetFollowerResponse> getFollower(User user) {

        List<User> findFollowers = followRepository.findByToUser(user);

        List<GetFollowerResponse> followerResponses = new ArrayList<>();
        for (User follower : findFollowers) {
            GetFollowerResponse followerResponse = GetFollowerResponse.builder()
                    .userId(follower.getId())
                    .nickname(follower.getNickname())
                    .build();
            followerResponses.add(followerResponse);
        }
        return followerResponses;
    }

    // 내가 팔로우하는 사람들 리스트 반환
    public List<GetFollowingResponse> getFollowing(User user) {

        List<User> findFollowings = followRepository.findByFromUser(user);

        List<GetFollowingResponse> followingResponses = new ArrayList<>();
        for (User following : findFollowings) {
            GetFollowingResponse followingResponse = GetFollowingResponse.builder()
                    .userId(following.getId())
                    .nickname(following.getNickname())
                    .build();
            followingResponses.add(followingResponse);
        }
        return followingResponses;
    }
}

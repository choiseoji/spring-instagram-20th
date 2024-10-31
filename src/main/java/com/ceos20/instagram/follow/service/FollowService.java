package com.ceos20.instagram.follow.service;

import com.ceos20.instagram.follow.domain.Follow;
import com.ceos20.instagram.follow.dto.GetFollowerResponse;
import com.ceos20.instagram.follow.dto.GetFollowingResponse;
import com.ceos20.instagram.follow.repository.FollowRepository;
import com.ceos20.instagram.member.domain.Member;
import com.ceos20.instagram.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowService {

    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    @Transactional
    public void createFollow(Member member, Long friendId) {

        Member friend = memberRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));

        Follow follow = Follow.toEntity(member, friend);
        followRepository.save(follow);
    }

    public List<GetFollowerResponse> getFollower(Long memberId, Member member) {

        Long targetMemberId = (memberId != null) ? memberId : member.getId();
        Member targetMember = memberRepository.findById(targetMemberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));

        List<Member> findFollowers = followRepository.findByToMember(targetMember);
        return findFollowers.stream()
                .map(follower -> GetFollowerResponse.fromEntity(follower))
                .collect(Collectors.toList());
    }

    public List<GetFollowingResponse> getFollowing(Long memberId, Member member) {

        Long targetMemberId = (memberId != null) ? memberId : member.getId();
        Member targetMember = memberRepository.findById(targetMemberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));

        List<Member> findFollowings = followRepository.findByFromMember(targetMember);
        return findFollowings.stream()
                .map(following -> GetFollowingResponse.fromEntity(following))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteFollow(Member member, Long friendId) {

        Member friend = memberRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));

        Follow follow = followRepository.findByFromMemberAndToMember(member, friend);
        followRepository.delete(follow);
    }
}

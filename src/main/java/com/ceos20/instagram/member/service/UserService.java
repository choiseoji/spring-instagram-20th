package com.ceos20.instagram.member.service;

import com.ceos20.instagram.member.domain.Member;
import com.ceos20.instagram.member.dto.GetUserInfoResponse;
import com.ceos20.instagram.member.dto.SaveUserRequest;
import com.ceos20.instagram.member.dto.UpdateUserInfoRequest;
import com.ceos20.instagram.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final MemberRepository memberRepository;

    // user 저장
    @Transactional
    public Long saveUser(SaveUserRequest saveUserRequest) {

        Member member = saveUserRequest.toEntity();
        return memberRepository.save(member).getId();
    }

    // user 정보 수정
    @Transactional
    public Long updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest, Member member) {

        member.updateInfo(
                updateUserInfoRequest.getUsername(),
                updateUserInfoRequest.getNickname(),
                updateUserInfoRequest.getPassword(),
                updateUserInfoRequest.getEmail(),
                updateUserInfoRequest.getImageUrl()
                );
        memberRepository.save(member);   // JPA의 변경감지 덕분에 생략 가능하다고 함

        return member.getId();
    }

    // user 정보 조회
    public GetUserInfoResponse getUserInfoById(Long userId) {

        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 user 입니다."));

        return GetUserInfoResponse.fromEntity(member);
    }
}

package com.ceos20.instagram.member.dto;

import com.ceos20.instagram.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserInfoResponse {

    private String username;

    private String nickname;

    private String email;

    private String imageUrl;

    public static GetUserInfoResponse fromEntity(Member member) {
        return GetUserInfoResponse.builder()
                .username(member.getUsername())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .imageUrl(member.getImageUrl())
                .build();
    }
}

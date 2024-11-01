package com.ceos20.instagram.comment.dto;

import com.ceos20.instagram.comment.domain.Comment;
import com.ceos20.instagram.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCommentResponse {

    private Long commentId;

    private String content;

    private Long authorId;

    private String authorNickname;

    public static GetCommentResponse fromEntity(Comment comment) {
        return GetCommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .authorId(comment.getAuthor().getId())
                .authorNickname(comment.getAuthor().getNickname())
                .build();
    }
}

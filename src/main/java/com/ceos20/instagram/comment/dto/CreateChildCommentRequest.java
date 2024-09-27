package com.ceos20.instagram.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateChildCommentRequest {

    private String content;

    private Long postId;

    private Long parentCommentId;
}

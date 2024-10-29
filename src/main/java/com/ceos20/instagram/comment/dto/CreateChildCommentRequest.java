package com.ceos20.instagram.comment.dto;

import com.ceos20.instagram.comment.domain.Comment;
import com.ceos20.instagram.post.domain.Post;
import com.ceos20.instagram.member.domain.Member;
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

    public Comment toEntity(Comment parentComment, Post post, Member author) {
        return Comment.builder()
                .content(content)
                .author(author)
                .parentComment(parentComment)
                .post(post)
                .build();
    }
}

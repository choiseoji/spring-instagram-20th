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
public class CreateCommentRequest {

    private String content;

    private Long postId;

    public Comment toEntity(Post post, Member author) {
        return Comment.builder()
                .content(content)
                .post(post)
                .author(author)
                .build();
    }
}

package com.ceos20.instagram.comment.service;

import com.ceos20.instagram.comment.domain.Comment;
import com.ceos20.instagram.comment.dto.CreateCommentRequest;
import com.ceos20.instagram.comment.dto.GetCommentResponse;
import com.ceos20.instagram.comment.dto.UpdateCommentRequest;
import com.ceos20.instagram.comment.repository.CommentRepository;
import com.ceos20.instagram.post.domain.Post;
import com.ceos20.instagram.post.repository.PostRepository;
import com.ceos20.instagram.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void createComment(CreateCommentRequest createCommentRequest, Member author, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        Comment comment = Comment.builder()
                .content(createCommentRequest.getContent())
                .post(post)
                .author(author)
                .build();
        commentRepository.save(comment);
    }

    @Transactional
    public void createChildComment(CreateCommentRequest createCommentRequest, Member author, Long parentCommentId) {

        Comment parentComment = commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 입니다."));

        Comment comment = Comment.builder()
                .content(createCommentRequest.getContent())
                .post(parentComment.getPost())
                .author(author)
                .parentComment(parentComment)
                .build();
        commentRepository.save(comment);
    }

    @Transactional
    public void updateContent(UpdateCommentRequest updateCommentRequest, Long commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 입니다."));
        comment.updateContent(updateCommentRequest.getContent());
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 입니다."));
        commentRepository.delete(comment);
    }

    public List<GetCommentResponse> getAllCommentsByPostId(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 post 입니다."));

        List<Comment> comments = commentRepository.findByPost(post);
        return comments.stream()
                .map(comment -> GetCommentResponse.fromEntity(comment))
                .collect(Collectors.toList());
    }
}

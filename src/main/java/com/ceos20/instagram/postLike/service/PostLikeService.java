package com.ceos20.instagram.postLike.service;

import com.ceos20.instagram.post.domain.Post;
import com.ceos20.instagram.post.repository.PostRepository;
import com.ceos20.instagram.postLike.domain.PostLike;
import com.ceos20.instagram.postLike.repository.PostLikeRepository;
import com.ceos20.instagram.user.domain.User;
import com.ceos20.instagram.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    // 본문 좋아요 처리
    public void handlePostLike(Long postId, Long userId) {

        Optional<PostLike> postLike = postLikeRepository.findByUserIdAndPostId(userId, postId);
        if (postLike.isPresent()) {
            deletePostLike(postLike.get());
        } else {
            createPostLike(postId, userId);
        }
    }

    // 본문 좋아요 삭제
    public void deletePostLike(PostLike postLike) {
        postLikeRepository.delete(postLike);
    }

    // 본문 좋아요 생성
    public void createPostLike(Long postId, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 userId 입니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 postId 입니다."));

        PostLike postLike = PostLike.builder()
                .post(post)
                .user(user)
                .build();
        postLikeRepository.save(postLike);
    }
}
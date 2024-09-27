package com.ceos20.instagram.postLike.repository;

import com.ceos20.instagram.post.domain.Post;
import com.ceos20.instagram.postLike.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByUserIdAndPostId(Long userId, Long postId);

    List<PostLike> findByPost(Post post);
}

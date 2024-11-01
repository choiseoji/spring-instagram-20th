package com.ceos20.instagram.postLike.service;

import com.ceos20.instagram.post.domain.Post;
import com.ceos20.instagram.post.repository.PostRepository;
import com.ceos20.instagram.postLike.domain.PostLike;
import com.ceos20.instagram.postLike.dto.GetUserLikeResponse;
import com.ceos20.instagram.postLike.repository.PostLikeRepository;
import com.ceos20.instagram.member.domain.Member;
import com.ceos20.instagram.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostLikeService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public void handlePostLike(Long postId, Long memberId) {

        Optional<PostLike> postLike = postLikeRepository.findByMemberIdAndPostId(memberId, postId);
        if (postLike.isPresent()) {
            deletePostLike(postLike.get());
        } else {
            createPostLike(postId, memberId);
        }
    }

    @Transactional
    public void deletePostLike(PostLike postLike) {
        postLikeRepository.delete(postLike);
    }

    @Transactional
    public void createPostLike(Long postId, Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 userId 입니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 postId 입니다."));

        PostLike postLike = PostLike.toEntity(member, post);
        postLikeRepository.save(postLike);
    }

    public List<GetUserLikeResponse> getUsersWhoLikePost(Long postId) {

        List<Member> members = postLikeRepository.findMemberWhoLikePostByPostId(postId);

        return members.stream()
                .map(user -> GetUserLikeResponse.fromEntity(user))
                .collect(Collectors.toList());
    }
}

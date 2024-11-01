package com.ceos20.instagram.post.service;

import com.ceos20.instagram.image.domain.Image;
import com.ceos20.instagram.image.repository.ImageRepository;
import com.ceos20.instagram.member.repository.MemberRepository;
import com.ceos20.instagram.post.domain.Post;
import com.ceos20.instagram.post.dto.CreatePostRequest;
import com.ceos20.instagram.post.dto.GetPostResponse;
import com.ceos20.instagram.post.dto.UpdatePostContentRequest;
import com.ceos20.instagram.post.repository.PostRepository;
import com.ceos20.instagram.member.domain.Member;
import com.ceos20.instagram.postLike.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public void createPost(CreatePostRequest createPostRequest, Member member) {

        Post post = Post.toEntity(createPostRequest, member);
        postRepository.save(post);

        List<Image> images = createPostRequest.getImages().stream()
                .map(image -> Image.toEntity(post, image))
                .collect(Collectors.toList());
        imageRepository.saveAll(images);
    }

    public List<GetPostResponse> getAllPosts() {

        List<Post> allPosts = postRepository.findAll();
        return allPosts.stream()
                .map(post -> GetPostResponse.fromEntity(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updatePostContent(UpdatePostContentRequest updatePostContentRequest, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 postId 입니다."));
        post.updateContent(updatePostContentRequest.getContent());
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 postId 입니다."));
        postLikeRepository.deleteByPostId(postId);
        postRepository.delete(post);
    }

    public GetPostResponse getPostById(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 postId 입니다."));

        return GetPostResponse.fromEntity(post);
    }

    public List<GetPostResponse> getAllPostsByMemberId(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 memberId 입니다."));
        List<Post> posts = postRepository.findByAuthor(member);

        return posts.stream()
                .map(post -> GetPostResponse.fromEntity(post))
                .collect(Collectors.toList());
    }
}

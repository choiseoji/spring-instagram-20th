package com.ceos20.instagram.post.service;

import com.ceos20.instagram.image.domain.Image;
import com.ceos20.instagram.image.repository.ImageRepository;
import com.ceos20.instagram.post.domain.Post;
import com.ceos20.instagram.post.dto.CreatePostRequest;
import com.ceos20.instagram.post.repository.PostRepository;
import com.ceos20.instagram.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

public class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks   // 모킹된 postRepository와 imageRepository를 postService에 주입
    private PostService postService;

    private User user;
    private Post post;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .id(1L)
                .username("seoji")
                .nickname("__seoji")
                .email("seoji@naver.com")
                .password("password")
                .build();

        post = Post.builder()
                .id(1L)
                .content("content")
                .build();
    }

    @Test
    @DisplayName("게시글 생성 테스트")
    void createPostTest() {
        // given
        CreatePostRequest request = CreatePostRequest.builder()
                .content("게시글 내용")
                .images(List.of(new CreatePostRequest.Image("imageUrl", 1L)))
                .build();

        // when
        postService.createPost(request, user);

        // then
        verify(postRepository, times(1)).save(any(Post.class));
        verify(imageRepository, times(1)).save(any(Image.class));
    }

    @Test
    @DisplayName("게시글 본문 수정 테스트")
    void updatePostContentTest() {
        // given
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));

        // when
        postService.updatePostContent("본문 수정", 1L);

        // then
        verify(postRepository, times(1)).findById(1L);
        verify(postRepository, times(1)).save(post);
        assertEquals("본문 수정", post.getContent());
    }
}

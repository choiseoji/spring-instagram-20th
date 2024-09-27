package com.ceos20.instagram.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    private String content;

    private List<Image> images;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Image {

        private String imageUrl;
        private Long index;
    }
}

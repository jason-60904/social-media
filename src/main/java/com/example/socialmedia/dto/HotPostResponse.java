package com.example.socialmedia.dto;

import lombok.Data;

@Data
public class HotPostResponse {
    private Integer postId;
    private String content;
    private Long commentCount;
}
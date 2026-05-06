package com.example.socialmedia.dto;

import lombok.Data;

@Data
public class UserResponse {

    private Integer userId;
    private String userName;
    private String phone;
    private String email;
    private String coverImage;
    private String biography;

}
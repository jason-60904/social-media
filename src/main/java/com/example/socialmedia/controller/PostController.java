package com.example.socialmedia.controller;

import com.example.socialmedia.entity.Post;
import com.example.socialmedia.service.PostService;
import com.example.socialmedia.dto.PostUpdateRequest;
import com.example.socialmedia.dto.HotPostResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // ✔ 1. 建立貼文
    @PostMapping
    public Post create(@RequestBody Post post) {
        return postService.createPost(post);
    }

    // ✔ 2. 查全部貼文（Feed）
    @GetMapping
    public List<Post> getAll() {
        return postService.getAllPosts();
    }

    // ✔ 3. 查某個使用者貼文（修正重點）
    @GetMapping("/user/{userId}")
    public List<Post> getByUser(@PathVariable Integer userId) {
        return postService.getPostsByUser(userId);
    }

    // ✔ 4. 更新貼文
    @PutMapping("/{postId}")
    public Post update(@PathVariable Integer postId,
                       @Valid @RequestBody PostUpdateRequest request) {
        return postService.updatePost(postId, request.getContent());
    }

    // ✔ 5. 刪除貼文
    @DeleteMapping("/{postId}")
    public String delete(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return "刪除成功";
    }

    // ✔ 6. 熱門貼文（Stored Procedure）
    @GetMapping("/hot")
    public List<HotPostResponse> hotPosts() {
        return postService.getHotPosts();
    }
}
package com.example.socialmedia.controller;

import com.example.socialmedia.entity.Comment;
import com.example.socialmedia.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //  新增留言
    @PostMapping
    public Comment create(@RequestBody Comment comment) {

        return commentService.createComment(comment);
    }

    //  查某篇貼文的留言
    @GetMapping("/post/{postId}")
    public List<Comment> getByPost(@PathVariable Integer postId) {

        return commentService.getCommentsByPost(postId);
    }
}
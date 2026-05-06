package com.example.socialmedia.controller;

import com.example.socialmedia.entity.Comment;
import com.example.socialmedia.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public Comment create(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }
}
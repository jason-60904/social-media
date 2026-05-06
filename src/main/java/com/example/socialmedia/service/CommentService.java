package com.example.socialmedia.service;

import com.example.socialmedia.entity.Comment;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.repository.CommentRepository;
import com.example.socialmedia.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    //  從 JWT 取當前使用者（phone）
    private String getCurrentUserPhone() {

        return (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    // 新增留言（JWT + XSS）
    public Comment createComment(Comment comment) {

        String phone = getCurrentUserPhone();

        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("使用者不存在"));

        String safeContent = (comment.getContent() == null)
                ? ""
                : HtmlUtils.htmlEscape(comment.getContent());

        comment.setContent(safeContent);

        //  userId 從 JWT 來
        comment.setUserId(user.getUserId());

        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    // 依貼文查留言（新功能）
    public List<Comment> getCommentsByPost(Integer postId) {

        return commentRepository.findByPostId(postId);
    }
}
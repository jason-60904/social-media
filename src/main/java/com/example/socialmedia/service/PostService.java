package com.example.socialmedia.service;

import com.example.socialmedia.entity.Post;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.repository.PostRepository;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.dto.HotPostResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // ⭐ 取得當前登入使用者（從 JWT 來）
    private String getCurrentUserPhone() {
        return (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    // ⭐ 發文（自動帶 userId + XSS 防護）
    public Post createPost(Post post) {

        String phone = getCurrentUserPhone();

        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("使用者不存在"));

        String safeContent = (post.getContent() == null)
                ? ""
                : HtmlUtils.htmlEscape(post.getContent());

        post.setContent(safeContent);
        post.setUserId(user.getUserId()); // ⭐ 自動設定
        post.setCreatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }

    public List<Post> getPostsByUser(Integer userId) {
        return postRepository.findByUserId(userId);
    }

    @SuppressWarnings("unchecked")
    public List<HotPostResponse> getHotPosts() {

        List<Object[]> result = entityManager
                .createNativeQuery("CALL get_hot_posts()")
                .getResultList();

        return result.stream().map(row -> {
            HotPostResponse dto = new HotPostResponse();
            dto.setPostId((Integer) row[0]);
            dto.setContent((String) row[1]);
            dto.setCommentCount((Long) row[2]);
            return dto;
        }).toList();
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // ⭐ 更新（含 XSS）
    public Post updatePost(Integer postId, String content) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("貼文不存在"));

        String safeContent = (content == null)
                ? ""
                : HtmlUtils.htmlEscape(content);

        post.setContent(safeContent);

        return postRepository.save(post);
    }

    public void deletePost(Integer postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("貼文不存在"));

        postRepository.delete(post);
    }
}
package com.example.socialmedia.service;

import com.example.socialmedia.entity.Post;
import com.example.socialmedia.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.example.socialmedia.dto.HotPostResponse;
import org.springframework.web.util.HtmlUtils;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public Post createPost(Post post) {
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

    public Post updatePost(Integer postId, String content) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("貼文不存在"));

        String safeContent = (content == null) ? "" : HtmlUtils.htmlEscape(content);

        post.setContent(safeContent);

        return postRepository.save(post);
    }

    public void deletePost(Integer postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("貼文不存在"));

        postRepository.delete(post);
    }

}
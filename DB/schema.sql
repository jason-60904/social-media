-- 使用資料庫
CREATE DATABASE IF NOT EXISTS social_db;
USE social_db;

-- ======================
-- Users
-- ======================
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       user_name VARCHAR(100),
                       phone VARCHAR(20) UNIQUE,
                       email VARCHAR(100) UNIQUE,
                       password VARCHAR(255),
                       cover_image VARCHAR(255),
                       biography TEXT,
                       created_at DATETIME
);

-- ======================
-- Posts
-- ======================
CREATE TABLE posts (
                       post_id INT AUTO_INCREMENT PRIMARY KEY,
                       content TEXT,
                       user_id INT,
                       image VARCHAR(255),
                       created_at DATETIME
);

-- ======================
-- Comments
-- ======================
CREATE TABLE comments (
                          comment_id INT AUTO_INCREMENT PRIMARY KEY,
                          content TEXT,
                          user_id INT,
                          post_id INT,
                          created_at DATETIME
);

-- ======================
-- Foreign Keys
-- ======================
ALTER TABLE posts
    ADD CONSTRAINT fk_post_user
        FOREIGN KEY (user_id) REFERENCES users(user_id)
            ON DELETE CASCADE;

ALTER TABLE comments
    ADD CONSTRAINT fk_comment_user
        FOREIGN KEY (user_id) REFERENCES users(user_id)
            ON DELETE CASCADE;

ALTER TABLE comments
    ADD CONSTRAINT fk_comment_post
        FOREIGN KEY (post_id) REFERENCES posts(post_id)
            ON DELETE CASCADE;
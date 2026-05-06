-- 使用資料庫
CREATE DATABASE IF NOT EXISTS social_db;

USE social_db;

-- ======================
-- Users
-- ======================

CREATE TABLE users (

                       user_id INT AUTO_INCREMENT PRIMARY KEY,

                       user_name VARCHAR(100) NOT NULL,

                       phone VARCHAR(20) NOT NULL UNIQUE,

                       email VARCHAR(100) UNIQUE,

                       password VARCHAR(255) NOT NULL,

                       cover_image VARCHAR(255),

                       biography TEXT,

                       created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ======================
-- Posts
-- ======================

CREATE TABLE posts (

                       post_id INT AUTO_INCREMENT PRIMARY KEY,

                       content TEXT,

                       user_id INT NOT NULL,

                       image VARCHAR(255),

                       created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ======================
-- Comments
-- ======================

CREATE TABLE comments (

                          comment_id INT AUTO_INCREMENT PRIMARY KEY,

                          content TEXT,

                          user_id INT NOT NULL,

                          post_id INT NOT NULL,

                          created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ======================
-- Foreign Keys
-- ======================

ALTER TABLE posts
    ADD CONSTRAINT fk_post_user
        FOREIGN KEY (user_id)
            REFERENCES users(user_id)
            ON DELETE CASCADE;

ALTER TABLE comments
    ADD CONSTRAINT fk_comment_user
        FOREIGN KEY (user_id)
            REFERENCES users(user_id)
            ON DELETE CASCADE;

ALTER TABLE comments
    ADD CONSTRAINT fk_comment_post
        FOREIGN KEY (post_id)
            REFERENCES posts(post_id)
            ON DELETE CASCADE;

-- ======================
-- Stored Procedure
-- ======================

DELIMITER //

CREATE PROCEDURE get_hot_posts()
BEGIN

SELECT
    p.post_id,
    p.content,
    COUNT(c.comment_id) AS comment_count

FROM posts p

         LEFT JOIN comments c
                   ON p.post_id = c.post_id

GROUP BY p.post_id

ORDER BY comment_count DESC;

END //

DELIMITER ;
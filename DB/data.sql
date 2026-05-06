USE social_db;

-- ======================
-- Users
-- ======================
INSERT INTO users (user_name, phone, email, password, biography, created_at)
VALUES
    ('user1', '0911111111', 'user1@test.com', 'hashed_pw', '我是 user1', NOW()),
    ('user2', '0922222222', 'user2@test.com', 'hashed_pw', '我是 user2', NOW());

-- ======================
-- Posts
-- ======================
INSERT INTO posts (content, user_id, created_at)
VALUES
    ('user1 第一篇貼文', 1, NOW()),
    ('user1 第二篇貼文', 1, NOW()),
    ('user2 第一篇貼文', 2, NOW());

-- ======================
-- Comments
-- ======================
INSERT INTO comments (content, user_id, post_id, created_at)
VALUES
    ('留言1', 1, 1, NOW()),
    ('留言2', 2, 1, NOW()),
    ('留言3', 1, 2, NOW());
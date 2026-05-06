USE social_db;

-- ======================
-- Users
-- ======================

-- 所有帳號密碼皆為：123456

INSERT INTO users (
    user_id,
    user_name,
    phone,
    email,
    password,
    cover_image,
    biography,
    created_at
)

VALUES

    (
        1,
        'test',
        '0912345678',
        'test@test.com',
        '$2a$10$3mUX1M.Thry0N6M.AwGC7eg6xZkWNEA71Yqb81.Ovmmdn34sQ3guW',
        NULL,
        '我是 test',
        '2026-05-05 15:19:26'
    ),

    (
        2,
        'qqqq',
        '11111111111',
        NULL,
        '$2a$10$Wh67hS373TuY9JQVT5AEY.Rq0JEilMdlm67ptpwy4fDwRD5yYp.ni',
        NULL,
        '我是 qqqq',
        '2026-05-06 21:12:36'
    ),

    (
        3,
        'zzz',
        '0123456789',
        NULL,
        '$2a$10$6yEWbcs2n9aS7k/5LIQQbucYq6IzBZ7CpWo48X5g8.DY.Rzu6nJK.',
        NULL,
        '我是 zzz',
        '2026-05-06 21:14:46'
    );

-- ======================
-- Posts
-- ======================

INSERT INTO posts (
    post_id,
    content,
    user_id,
    image,
    created_at
)

VALUES

    (
        1,
        '今天開始學 JWT',
        1,
        NULL,
        '2026-05-06 18:00:00'
    ),

    (
        2,
        'Spring Boot + Vue 成功串接',
        2,
        NULL,
        '2026-05-06 19:00:00'
    ),

    (
        3,
        '熱門貼文功能完成',
        3,
        NULL,
        '2026-05-06 20:00:00'
    );

-- ======================
-- Comments
-- ======================

INSERT INTO comments (
    comment_id,
    content,
    user_id,
    post_id,
    created_at
)

VALUES

    (
        1,
        '太強了',
        2,
        1,
        '2026-05-06 20:10:00'
    ),

    (
        2,
        'JWT 很實用',
        3,
        1,
        '2026-05-06 20:15:00'
    ),

    (
        3,
        'Vue Feed 很像 Threads',
        1,
        2,
        '2026-05-06 20:20:00'
    ),

    (
        4,
        '熱門貼文完成了',
        1,
        3,
        '2026-05-06 20:30:00'
    );
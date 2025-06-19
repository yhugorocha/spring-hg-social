CREATE TABLE post_likes (
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (post_id, user_id),

    CONSTRAINT fk_post_likes_post FOREIGN KEY (post_id) REFERENCES tb_posts(id) ON DELETE CASCADE,
    CONSTRAINT fk_post_likes_user FOREIGN KEY (user_id) REFERENCES tb_users(id) ON DELETE CASCADE
);
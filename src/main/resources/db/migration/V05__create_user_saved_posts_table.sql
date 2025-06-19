CREATE TABLE user_saved_posts (
    user_id BIGINT NOT NULL,
    post_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, post_id),
    CONSTRAINT fk_user_saved_posts_user FOREIGN KEY (user_id) REFERENCES tb_users(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_saved_posts_post FOREIGN KEY (post_id) REFERENCES tb_posts(id) ON DELETE CASCADE
);
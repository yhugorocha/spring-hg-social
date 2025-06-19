CREATE TABLE user_following (
    follower_id BIGINT NOT NULL,
    followed_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (follower_id, followed_id),
    CONSTRAINT fk_user_following_follower FOREIGN KEY (follower_id) REFERENCES tb_users(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_following_followed FOREIGN KEY (followed_id) REFERENCES tb_users(id) ON DELETE CASCADE
);
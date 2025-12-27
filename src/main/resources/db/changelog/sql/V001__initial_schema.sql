-- liquibase formatted sql

-- changeset francisco:001

CREATE TABLE accounts (
                          id BIGSERIAL PRIMARY KEY,
                          username VARCHAR(100) NOT NULL UNIQUE,
                          name VARCHAR(150) NOT NULL,
                          code VARCHAR(100) NOT NULL,
                          email VARCHAR(150) NOT NULL UNIQUE,
                          password VARCHAR(255) NOT NULL,
                          role VARCHAR(50) NOT NULL,
                          education_level VARCHAR(50),
                          is_scholarship BOOLEAN,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE posts (
                       id BIGSERIAL PRIMARY KEY,
                       author_id BIGINT NOT NULL,
                       content TEXT NOT NULL,
                       approved BOOLEAN NOT NULL,
                       number_of_likes BIGINT,
                       number_of_comments BIGINT,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                           CONSTRAINT fk_posts_author
                           FOREIGN KEY (author_id)
                               REFERENCES accounts(id)
);

CREATE TABLE posts_media (
                             post_id BIGINT NOT NULL,
                             media_value TEXT,

                             CONSTRAINT fk_posts_media_post
                                 FOREIGN KEY (post_id)
                                     REFERENCES posts(id)
                                     ON DELETE CASCADE
);

CREATE TABLE comments (
                          id BIGSERIAL PRIMARY KEY,
                          author_id BIGINT NOT NULL,
                          post_id BIGINT NOT NULL,
                          content TEXT NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          CONSTRAINT fk_comments_author
                              FOREIGN KEY (author_id)
                                  REFERENCES accounts(id),

                          CONSTRAINT fk_comments_post
                              FOREIGN KEY (post_id)
                                  REFERENCES posts(id)
                                  ON DELETE CASCADE
);

CREATE TABLE post_likes (
                            post_id BIGINT NOT NULL,
                            user_id BIGINT NOT NULL,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                            CONSTRAINT pk_post_likes PRIMARY KEY (post_id, user_id),

                            CONSTRAINT fk_post_likes_post
                                FOREIGN KEY (post_id)
                                    REFERENCES posts(id)
                                    ON DELETE CASCADE,

                            CONSTRAINT fk_post_likes_user
                                FOREIGN KEY (user_id)
                                    REFERENCES accounts(id)
                                    ON DELETE CASCADE
);

CREATE INDEX idx_post_likes_post ON post_likes(post_id);

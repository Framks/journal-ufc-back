-- liquibase formatted sql

-- changeset francisco:002

CREATE TABLE tags (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE post_tags (
                           post_id BIGINT NOT NULL,
                           tag_id BIGINT NOT NULL,

                           CONSTRAINT pk_post_tags PRIMARY KEY (post_id, tag_id),

                           CONSTRAINT fk_post_tags_post
                               FOREIGN KEY (post_id)
                                   REFERENCES posts(id)
                                   ON DELETE CASCADE,

                           CONSTRAINT fk_post_tags_tag
                               FOREIGN KEY (tag_id)
                                   REFERENCES tags(id)
                                   ON DELETE CASCADE
);

CREATE INDEX idx_post_tags_post ON post_tags(post_id);
CREATE INDEX idx_post_tags_tag ON post_tags(tag_id);

ALTER TABLE posts
    ADD COLUMN is_featured BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE posts
    ALTER COLUMN approved SET DEFAULT FALSE;

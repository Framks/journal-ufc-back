insert into accounts (id, username, name, code, email, password, role, education_level, is_scholarship, created_at, updated_at) values
(1, 'xingling', 'Franscisco', 'ADM_b0688012-f901-4a87-97f3-119b5dd470a9', 'string@gmail.com', '$2a$10$Yipk9YH2M0Z9Kxc4lAg/eOD6ZQiWQ9iVIVtB5WQoSP0e.FQpBYPz2', 'ADMIN', null, null, '2025-12-27 13:35:36.77073', '2025-12-27 13:35:36.770769'),
(2, 'little_teacher', 'juniro', 'PRO_1ee89222-edda-4a91-8bec-4958cf9311b2', 'junin@gmail.com', '$2a$10$GM/U4LzLLiqWvE3/6kQUxOc.q1cEbXadWD3yDr/JlyPA3sR3EH1hq', 'TEACHER', null, null, '2025-12-27 14:03:06.425128', '2025-12-27 14:03:06.425138'),
(3, 'little_pig', 'little children', 'ALU_9b77b6ef-e66b-4f87-925f-61dfd583d267', 'aluno@gmail.com', '$2a$10$1Lw8tmdXTNgP/C3vXTgf9OVqdcE.Ug/gB7NxEqTCYWURwB8Ac9YjG', 'STUDENT', 'UNDERGRADUATE', TRUE, '2025-12-27 14:06:35.529681', '2025-12-27 14:06:35.529687');


insert into posts ( id, author_id, content, approved, number_of_likes, number_of_comments, is_featured, deleted, created_at, updated_at) values
(1, 1, 'TEste para ver se deu tudo certo com a cagada da que fizemos de forma explendorozamente desagradavel', TRUE, 0, 0, TRUE, FALSE, '2025-12-27 14:11:23.081503', '2025-12-27 14:11:23.081513');

insert into tags (id, name) values
(1, 'pinguirm'),
(2, 'praia');

insert into posts_media (post_id, media_value) values
(1, 'https://ufsb.edu.br/residenciapedagogica/images/phocagallery/galeria2/thumbs/phoca_thumb_l_image03_grd.png');

insert into post_tags(post_id, tag_id) values
(1, 1),
(1, 2);
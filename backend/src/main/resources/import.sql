INSERT INTO tb_user (name, email, birth_date, url_image, registration_date) VALUES ('Jose Carlos', 'jose@gmail.com', '1990-05-17',  'https://avatars.githubusercontent.com/u/100246121?s=64&v=4', '2022-10-01');
INSERT INTO tb_user (name, email, birth_date, url_image, registration_date) VALUES ('Clara Rita', 'rita@gmail.com', '1980-05-17',  'https://avatars.githubusercontent.com/u/100246121?s=64&v=4', '2022-10-01');

INSERT INTO tb_category (name, description) VALUES ('Esporte', 'Contrary to popular');
INSERT INTO tb_category (name, description) VALUES ('Economia', 'The standard chunk');

INSERT INTO tb_post (title, title_description, description, date_time, user_id, category_id) VALUES ('Nova contratação no Flamengo', 'But I must explain to you how all this mistaken', 'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate', TIMESTAMP WITH TIME ZONE '2022-10-06', 1, 1);
INSERT INTO tb_post (title, title_description, description, date_time, user_id, category_id) VALUES ('Que na bolsa de valores', 'But I must explain to you how all this mistaken', 'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate', TIMESTAMP WITH TIME ZONE '2022-10-06', 2, 2);

INSERT INTO tb_comment (comment_description, date_Time, post_id, user_id) VALUES ('provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita', TIMESTAMP WITH TIME ZONE '2022-07-26', 1, 1);
INSERT INTO tb_comment (comment_description, date_Time, post_id, user_id) VALUES ('rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae', TIMESTAMP WITH TIME ZONE '2022-07-27', 1, 2);

INSERT INTO tb_comment (comment_description, date_Time, post_id, user_id) VALUES ('but because those who do not know how to pursue pleasure', TIMESTAMP WITH TIME ZONE '2022-07-28', 2, 2);
INSERT INTO tb_comment (comment_description, date_Time, post_id, user_id) VALUES ('chooses to enjoy a pleasure that has no annoying consequences, or one who avoids', TIMESTAMP WITH TIME ZONE '2022-07-26', 2, 1);
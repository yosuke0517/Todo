INSERT INTO user_roles (user_id, role_key, created_by, created_at, version) VALUES
((SELECT user_id FROM users WHERE email = 'aaa@mail.com'), 'user', 'none', NOW(), 1);

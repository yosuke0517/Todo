INSERT INTO role_permissions (role_key, permission_id, created_by, created_at, version) VALUES
('system_admin', (SELECT permission_id FROM permissions WHERE permission_key = '.*'), 'none', NOW(), 1),
('user', (SELECT permission_id FROM permissions WHERE permission_key = '.*'), 'none', NOW(), 1);
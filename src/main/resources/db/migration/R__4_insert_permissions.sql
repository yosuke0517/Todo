INSERT INTO permissions (category_key, permission_key, permission_name, created_by, created_at, version) VALUES
('*', '.*', '全操作', 'none', NOW(), 1),
('code', '^Code\\.(find|show|download)Code$', 'コード検索', 'none', NOW(), 1),
('code', '^Code\\.(new|edit)Code$', 'コード登録・編集', 'none', NOW(), 1),
('home', '^Home\\.index$', 'ホーム索引', 'none', NOW(), 1),
('role', '^Role\\.(find|show|download)Role$', '役割検索', 'none', NOW(), 1),
('role', '^Role\\.(new|edit)Role$', '役割登録・編集', 'none', NOW(), 1),
('upload', '^UploadFiles\\..*', 'ファイルアップロード', 'none', NOW(), 1),
('user', '^User\\.(find|show|downloadCsv|downloadExcel|downloadPdf)User$', 'ユーザー検索', 'none', NOW(), 1),
('user', '^User\\.(new|edit)User$', 'ユーザー登録・編集', 'none', NOW(), 1),
('staff', '^Staff\\.(find|show|download)Staff$', '担当者検索', 'none', NOW(), 1),
('staff', '^Staff\\.(new|edit)Staff$', '担当者登録・編集', 'none', NOW(), 1);
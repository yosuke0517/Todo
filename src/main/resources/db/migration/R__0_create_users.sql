CREATE TABLE IF NOT EXISTS users(
  user_id INT(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ユーザID'
  , name VARCHAR(100) DEFAULT NULL COMMENT '名前'
  , email VARCHAR(100) DEFAULT NULL COMMENT 'メールアドレス'
  , password VARCHAR(100) DEFAULT NULL COMMENT 'パスワード'
  , created_by VARCHAR(50) NOT NULL COMMENT '登録者'
  , created_at DATETIME NOT NULL COMMENT '登録日時'
  , updated_by VARCHAR(50) DEFAULT NULL COMMENT '更新者'
  , updated_at DATETIME DEFAULT NULL COMMENT '更新日時'
  , deleted_by VARCHAR(50) DEFAULT NULL COMMENT '削除者'
  , deleted_at DATETIME DEFAULT NULL COMMENT '削除日時'
  , version INT(11) unsigned NOT NULL DEFAULT 1 COMMENT '改訂番号'
  , PRIMARY KEY (user_id)
  , KEY idx_users (email, deleted_at)
) COMMENT='ユーザー';

CREATE TABLE IF NOT EXISTS roles(
  role_id INT(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '役割ID'
  , role_key VARCHAR(100) NOT NULL COMMENT '役割キー'
  , role_name VARCHAR(100) NOT NULL COMMENT '役割名'
  , created_by VARCHAR(50) NOT NULL COMMENT '登録者'
  , created_at DATETIME NOT NULL COMMENT '登録日時'
  , updated_by VARCHAR(50) DEFAULT NULL COMMENT '更新者'
  , updated_at DATETIME DEFAULT NULL COMMENT '更新日時'
  , deleted_by VARCHAR(50) DEFAULT NULL COMMENT '削除者'
  , deleted_at DATETIME DEFAULT NULL COMMENT '削除日時'
  , version INT(11) unsigned NOT NULL DEFAULT 1 COMMENT '改訂番号'
  , PRIMARY KEY (role_id)
  , KEY idx_roles (role_key, deleted_at)
) COMMENT='役割';

CREATE TABLE IF NOT EXISTS user_roles(
  user_role_id INT(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ユーザー役割ID'
  , user_id INT(11) unsigned NOT NULL COMMENT 'ユーザーID'
  , role_key VARCHAR(100) NOT NULL COMMENT '役割キー'
  , created_by VARCHAR(50) NOT NULL COMMENT '登録者'
  , created_at DATETIME NOT NULL COMMENT '登録日時'
  , updated_by VARCHAR(50) DEFAULT NULL COMMENT '更新者'
  , updated_at DATETIME DEFAULT NULL COMMENT '更新日時'
  , deleted_by VARCHAR(50) DEFAULT NULL COMMENT '削除者'
  , deleted_at DATETIME DEFAULT NULL COMMENT '削除日時'
  , version INT(11) unsigned NOT NULL DEFAULT 1 COMMENT '改訂番号'
  , PRIMARY KEY (user_role_id)
  , KEY idx_user_roles (user_id, role_key, deleted_at)
) COMMENT='ユーザー役割';

CREATE TABLE IF NOT EXISTS permissions(
  permission_id INT(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '権限ID'
  , category_key VARCHAR(50) NOT NULL COMMENT '権限カテゴリキー'
  , permission_key VARCHAR(100) NOT NULL COMMENT '権限キー'
  , permission_name VARCHAR(50) NOT NULL COMMENT '権限名'
  , created_by VARCHAR(50) NOT NULL COMMENT '登録者'
  , created_at DATETIME NOT NULL COMMENT '登録日時'
  , updated_by VARCHAR(50) DEFAULT NULL COMMENT '更新者'
  , updated_at DATETIME DEFAULT NULL COMMENT '更新日時'
  , deleted_by VARCHAR(50) DEFAULT NULL COMMENT '削除者'
  , deleted_at DATETIME DEFAULT NULL COMMENT '削除日時'
  , version INT(11) unsigned NOT NULL DEFAULT 1 COMMENT '改訂番号'
  , PRIMARY KEY (permission_id)
  , KEY idx_permissions (permission_key, deleted_at)
) COMMENT='権限';

CREATE TABLE IF NOT EXISTS role_permissions(
  role_permission_id INT(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '役割権限紐付けID'
  , role_key VARCHAR(100) NOT NULL COMMENT '役割キー'
  , permission_id INT(11) NOT NULL COMMENT '権限ID'
  , created_by VARCHAR(50) NOT NULL COMMENT '登録者'
  , created_at DATETIME NOT NULL COMMENT '登録日時'
  , updated_by VARCHAR(50) DEFAULT NULL COMMENT '更新者'
  , updated_at DATETIME DEFAULT NULL COMMENT '更新日時'
  , deleted_by VARCHAR(50) DEFAULT NULL COMMENT '削除者'
  , deleted_at DATETIME DEFAULT NULL COMMENT '削除日時'
  , version INT(11) unsigned NOT NULL DEFAULT 1 COMMENT '改訂番号'
  , PRIMARY KEY (role_permission_id)
  , KEY idx_role_permissions (role_key, deleted_at)
) COMMENT='役割権限紐付け';

CREATE TABLE IF NOT EXISTS todoes(
  todo_id INT(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'TODO_ID'
  , user_id INT(11) unsigned NOT NULL COMMENT 'ユーザーID'
  , todo_category INT(3) NOT NULL COMMENT 'TODOカテゴリ'
  , subject VARCHAR(50) NOT NULL COMMENT '件名'
  , details VARCHAR(255) NOT NULL COMMENT '内容'
  , created_by VARCHAR(50) NOT NULL COMMENT '登録者'
  , created_at DATETIME NOT NULL COMMENT '登録日時'
  , updated_by VARCHAR(50) DEFAULT NULL COMMENT '更新者'
  , updated_at DATETIME DEFAULT NULL COMMENT '更新日時'
  , deleted_by VARCHAR(50) DEFAULT NULL COMMENT '削除者'
  , deleted_at DATETIME DEFAULT NULL COMMENT '削除日時'
  , version INT(11) unsigned NOT NULL DEFAULT 1 COMMENT '改訂番号'
  , PRIMARY KEY (todo_id)
  , KEY idx_todo (todo_category, deleted_at)
) COMMENT='TODO';


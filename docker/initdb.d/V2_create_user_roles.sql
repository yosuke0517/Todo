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
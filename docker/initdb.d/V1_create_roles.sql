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
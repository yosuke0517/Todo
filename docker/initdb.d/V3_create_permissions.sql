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
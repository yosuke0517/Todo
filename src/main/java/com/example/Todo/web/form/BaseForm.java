package com.example.Todo.web.form;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Formを作成するときに必ず組み込むべきであろう値
 */
@Data
public abstract class BaseForm implements Serializable {

    // 作成・更新者に使用する値
    String auditUser;

    // 作成・更新日に使用する値
    LocalDateTime auditDateTime;

    // 改定番号
    Integer version;

    /**
     * 既存レコードがないデータであるか
     *
     * @return
     */
    public boolean isNew() {
        return getId() == null;
    }

    /**
     * IdカラムのGetter
     *
     * @return
     */
    public abstract Long getId();
}

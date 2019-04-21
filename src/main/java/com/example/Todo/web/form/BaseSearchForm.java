package com.example.Todo.web.form;

import lombok.Data;

/**
 * DomaUtilsで定義されているcreateSelectOptionsで使用するページングの設定を定義する
 */
@Data
public abstract class BaseSearchForm extends BaseForm {

    int page = 1;

    int perpage = 10;
}

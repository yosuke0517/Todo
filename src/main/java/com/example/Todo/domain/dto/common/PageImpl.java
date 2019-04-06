package com.example.Todo.domain.dto.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
public class PageImpl<T> implements Page<T>, Serializable {

    List<T> data;

    long count = -1;

    int page = 1;

    int perpage = 10;

    int totalPages = -1;

    /**
     * コンストラクタ
     *
     * @param data
     * @param pageable
     */
    public PageImpl(List<T> data, Pageable pageable, long count) {
        this.data = (data == null) ? Collections.emptyList() : data;
        this.count = count;
        this.page = pageable.getPage();
        this.perpage = pageable.getPerpage();
        this.totalPages = Math.max(1, (int) Math.ceil((double) count / perpage));
    }
}

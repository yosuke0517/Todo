package com.example.Todo.domain.dto.common;

import java.util.List;

/**
 * ページファクトリのデフォルト実装
 */
public class PageFactoryImpl implements PageFactory {

    /**
     * インスタンスを生成して返します。
     *
     * @param <T>
     * @param data
     * @param pageable
     * @param count
     * @return
     */
    public <T> Page<T> create(List<T> data, Pageable pageable, long count) {
        return new PageImpl<>(data, pageable, count);
    }
}

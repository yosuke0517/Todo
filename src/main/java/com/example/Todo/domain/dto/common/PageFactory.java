package com.example.Todo.domain.dto.common;

import java.util.List;

/**
 * ページファクトリ
 */
public interface PageFactory {

/**
 * インスタンスを生成して返します。
 *
 * @param <T>
 * @param data
 * @param pageable
 * @param count
 * @return
 **/
     <T> Page<T> create(List<T> data, Pageable pageable, long count);
}

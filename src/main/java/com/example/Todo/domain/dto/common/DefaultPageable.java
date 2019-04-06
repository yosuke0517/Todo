package com.example.Todo.domain.dto.common;

import lombok.Data;

@Data
public class DefaultPageable implements Pageable {

    int page = 1;

    int perpage = 10;

    public DefaultPageable(int page, int perpage) {
        this.page = page;
        this.perpage = perpage;
    }
}

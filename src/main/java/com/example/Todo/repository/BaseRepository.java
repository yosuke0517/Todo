package com.example.Todo.repository;

import com.example.Todo.domain.dto.common.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseRepository {

    @Autowired
    protected PageFactory pageFactory;
}

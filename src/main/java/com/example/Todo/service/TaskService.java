package com.example.Todo.service;

import com.example.Todo.domain.dto.Task;
import com.example.Todo.domain.dto.common.Page;
import com.example.Todo.domain.dto.common.Pageable;
import com.example.Todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public Task createTask(final Task inputTask){

        //TODO セキュリティー対策はいらないか？
        taskRepository.create(inputTask);
        return inputTask;
    }

    public Page<Task> findAllTask(Long userId, Pageable pageable){
        //nullチェック必要かな？
        return taskRepository.findAll(userId,pageable);
    }


}

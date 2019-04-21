package com.example.Todo.repository;

import com.example.Todo.domain.dao.TaskDao;
import com.example.Todo.domain.dto.Task;
import com.example.Todo.domain.dto.User;
import com.example.Todo.domain.dto.common.Page;
import com.example.Todo.domain.dto.common.Pageable;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.example.Todo.common.utils.DomaUtils.createSelectOptions;
import static java.util.stream.Collectors.toList;

@Repository
public class TaskRepository extends BaseRepository{

    @Autowired
    TaskDao taskDao;

    public Page<Task> findAll(Long userId, Pageable pageable){
        //　ページングを指定する
        val options = createSelectOptions(pageable).count();
        val data = taskDao.selectAll(userId, options, toList());

        return pageFactory.create(data, pageable,options.getCount());
    }


    /**
     * タスク登録
     * @param inputTask
     * @return
     */
    public Task create(final Task inputTask) {
        //1件登録
        taskDao.insert(inputTask);

        return inputTask;
    }
}

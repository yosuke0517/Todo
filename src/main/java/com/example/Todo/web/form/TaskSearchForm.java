package com.example.Todo.web.form;

import com.example.Todo.domain.dto.common.Pageable;
import lombok.Data;

@Data
public class TaskSearchForm extends BaseSearchForm implements Pageable {

    Long id;

    Long user_id;

    int task_category;

    String subject;

    String details;

}

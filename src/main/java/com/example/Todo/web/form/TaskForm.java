package com.example.Todo.web.form;

import com.example.Todo.domain.dto.common.Pageable;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class TaskForm extends BaseSearchForm implements Pageable {

    Long id;

    Long user_id;

    @NotEmpty
    int task_category;

    @NotEmpty
    String subject;

    String details;
}

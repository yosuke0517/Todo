package com.example.Todo.web.form;

import com.example.Todo.domain.dto.common.Pageable;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class UserForm extends BaseSearchForm implements Pageable {
    Long id;

    @NotEmpty
    String name;

    @NotEmpty
    @Email
    String email;

    @NotEmpty
    String password;

    @NotEmpty
    String passwordConfirm;
}

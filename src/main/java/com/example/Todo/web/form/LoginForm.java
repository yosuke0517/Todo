package com.example.Todo.web.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class LoginForm implements Serializable {

    private static final long serialVersionUID = 7593564324192730932L;

    @NotEmpty
    @Email
    String email;

    @NotEmpty
    String password;

    // ログインしたままにするか
    boolean rememberMe;
}

package com.example.Todo.domain.validation;

import com.example.Todo.web.form.UserForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import static com.example.Todo.common.utils.ValidateUtils.isEquals;

/**
 * ユーザ登録時のチェック事項
 */
@Component
public class UserFormValidator extends AbstractValidator<UserForm> {

    @Override
    protected void doValidate(UserForm form, Errors errors){
        //確認パスワードと突き合わせる
        if(!isEquals(form.getPassword(), form.getPasswordConfirm())){
            errors.rejectValue("password","users.unmatchPassword");
            errors.rejectValue("passwordConfirm","users.unmatchPassword");
        }
    }
}

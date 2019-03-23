package com.example.Todo.security;


import com.example.Todo.domain.dto.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@Data
public class LoginUser extends org.springframework.security.core.userdetails.User {
    /**
     * コンストラクタ
     *
     * @param user
     * @param authorities
     */
    public LoginUser(User user, Collection<? extends GrantedAuthority> authorities){
        super(String.valueOf(user.getEmail()), user.getPassword(), authorities);
    }

}

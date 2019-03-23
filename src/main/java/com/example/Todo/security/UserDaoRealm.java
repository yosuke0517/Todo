package com.example.Todo.security;

import com.example.Todo.common.security.BaseRealm;
import com.example.Todo.domain.dao.UserDao;
import com.example.Todo.domain.dao.UserRoleDao;
import com.example.Todo.domain.dto.User;
import com.example.Todo.domain.dto.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.seasar.doma.jdbc.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Component
@Slf4j
public class UserDaoRealm extends BaseRealm {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Override
    protected UserDetails getLoginUser(String email) {
        User user = null;

        List<GrantedAuthority> authorityList = null;

        try{
            user = new User();
            user.setEmail(email);

            //ユーザを取得して、セッションに保存
            user = userDao.select(user)
                    .orElseThrow(() -> new UsernameNotFoundException("no user found. [id=]" + email + "]"));


            //担当者権限を取得する
            List<UserRole> userRoles = userRoleDao.selectByUserId(user.getId(), toList());

            //役割キーにプレフィックスをつけてまとめる
            Set<String> roleKeys = userRoles.stream().map(UserRole::getRole_key).collect(toSet());

            //権限キーをまとめる
            Set<String> permissionKeys = userRoles.stream().map(UserRole::getPermissionKey).collect(toSet());

            //役割と権限を両方ともGrantedAuthorityとして渡す
            Set<String> authorities = new HashSet<>();
            authorities.addAll(roleKeys);
            authorities.addAll(permissionKeys);
            authorityList = AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));
        }catch (Exception e){
            // 0件例外がスローされた場合は何もしない
            // それ以外の場合は、認証エラーの例外で包む
            if(!(e instanceof NoResultException)){
                throw new UsernameNotFoundException("could not select user,", e);
            }
        }
        return new LoginUser(user, authorityList);
    }
}

package com.example.Todo.domain.dao;

import com.example.Todo.domain.dto.User;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface UserDao {
    /**
     * ユーザーを取得します。
     */
    @Select
    List<User> selectAll();

    @Select
    Optional<User> select(User user);


    @Insert
    @Transactional
    int insert(User user);


}

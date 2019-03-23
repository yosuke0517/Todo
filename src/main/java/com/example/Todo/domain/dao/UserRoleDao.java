package com.example.Todo.domain.dao;

import com.example.Todo.domain.dto.UserRole;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface UserRoleDao {

    //@Select
    //String selectByUserId(Long id);

    /**
     * 権限を取得します。
     *
     * @param id
     * @param collector
     * @param <R>
     * @return
     */
    @Select(strategy = SelectType.COLLECT)
    <R> R selectByUserId(Long id, final Collector<UserRole, ?, R> collector);

    @Insert(exclude = {"roleName", "permissionKey", "permissionName"})
    int insert(UserRole userRole);


}

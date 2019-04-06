package com.example.Todo.service;

import com.example.Todo.domain.dao.UserDao;
import com.example.Todo.domain.dao.UserRoleDao;
import com.example.Todo.domain.dto.User;
import com.example.Todo.domain.dto.UserCriteria;
import com.example.Todo.domain.dto.common.Page;
import com.example.Todo.domain.dto.common.Pageable;
import com.example.Todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Autowired
    UserRepository userRepository;

    /**
     * 全件取得
     * @return users
     */
    @Transactional(readOnly = true)
    public Page<User> selectAll(UserCriteria criteria, Pageable pageable){
        //nullチェック
        Assert.notNull(criteria, "criteria must not be null");
        return userRepository.findAll(criteria,pageable);
    }

    /**
     * ユーザーを取得します。
     *
     * @return
     */
    @Transactional(readOnly = true)
    public User findById(final Long id) {
        Assert.notNull(id, "id must not be null");
        return userRepository.findById(id);
    }

    /**
     * ユーザーを追加します。
     *
     * @param inputUser
     * @return inputUser(登録ユーザ)
     */
    public User create(final User inputUser) {
        Assert.notNull(inputUser, "inputUser must not be null");
        userDao.insert(inputUser);

        return inputUser;
    }

}

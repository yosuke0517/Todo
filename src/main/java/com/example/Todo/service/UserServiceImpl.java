package com.example.Todo.service;

import com.example.Todo.domain.dto.User;
import com.example.Todo.domain.dto.UserCriteria;
import com.example.Todo.domain.dto.common.Page;
import com.example.Todo.domain.dto.common.Pageable;
import com.example.Todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {

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
     * ユーザーを取得します。(id)
     *
     * @return
     */
    @Transactional(readOnly = true)
    public User findById(final Long id) {
        Assert.notNull(id, "id must not be null");
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public User findByEmail(final String email){
        Assert.notNull(email,"email must not be null");
        return userRepository.findByEmail(email);
    }

    /**
     * ユーザーを追加します。
     *
     * @param inputUser
     * @return inputUser(登録ユーザ)
     */
    public User create(final User inputUser) {
        Assert.notNull(inputUser, "inputUser must not be null");
        //なぜかここだけDaoからだったのでRepositoryからに変更20190413
        //userDao.insert(inputUser);
        userRepository.create(inputUser);

        return inputUser;
    }

}

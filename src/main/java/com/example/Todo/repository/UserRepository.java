package com.example.Todo.repository;

import com.example.Todo.domain.dao.UserDao;
import com.example.Todo.domain.dao.UserRoleDao;
import com.example.Todo.domain.dto.User;
import com.example.Todo.domain.dto.UserCriteria;
import com.example.Todo.domain.dto.common.Page;
import com.example.Todo.domain.dto.common.Pageable;
import com.example.Todo.domain.exception.NoDataFoundException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.Optional;

import static com.example.Todo.common.utils.DomaUtils.createSelectOptions;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Repository
public class UserRepository extends BaseRepository {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    /**
     * ユーザーを取得します。
     *
     * @param criteria
     * @param pageable
     * @return
     */
    public Page<User> findAll(UserCriteria criteria, Pageable pageable){
        //　ページングを指定する
        val options = createSelectOptions(pageable).count();
        val data = userDao.selectAll(criteria, options, toList());
        return pageFactory.create(data, pageable,options.getCount());
    }

    /**
     * ユーザを1件取得する
     * @param criteria
     * @return
     */
    public Optional<User> findOne(UserCriteria criteria){
        //1件取得
        val user = userDao.select(criteria);

        // 添付ファイルを取得する　　あとで実装
//        user.ifPresent(u -> {
//            val uploadFileId = u.getUploadFileId();
//            val uploadFile = ofNullable(uploadFileId).map(uploadFileDao::selectById);
//            uploadFile.ifPresent(u::setUploadFile);
//        });
        return user;
    }

    public User findById(final Long id) {
        //orElseThrow→ Optionalに値がなければ指定した例外をThrowします
        return userDao.selectById(id).orElseThrow(() -> new NoDataFoundException("user_id=" + id + " のデータが見つかりません。"));
    }

}

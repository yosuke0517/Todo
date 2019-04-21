package com.example.Todo.domain.dao;

import com.example.Todo.domain.dto.User;
import com.example.Todo.domain.dto.UserCriteria;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.stream.Collector;

@Dao
@ConfigAutowireable
public interface UserDao {
    /**
     * ユーザーを取得します。
     */
    @Select(strategy = SelectType.COLLECT)//Collect検索 を使用する宣言
    <R> R selectAll(final UserCriteria criteria, final SelectOptions options, final Collector<User, ?, R> collector);
    //Daoメソッドの引数に Collector を渡すことで任意の型で結果を受け取ることができる
    //戻り値は任意型にしたいので R 、引数には Collector<Student, ?, R> を受け取るようにする
    //List＜Student＞ findAll();じゃいけないのか...コレだとList型しか受け取れない
    //呼び出しを下記のようにすれば年齢でMapした結果が受け取れる
    // Map＜Age, List＜User＞＞ UserMap = UserDao.findAll(Collectors.groupingBy(User::getAge));
    //List<User>で受け取りたい場合はList＜User＞ UserList = UserDao.findAll(Collectors.toList());

    /**
     * ユーザを1件取得します。
     *
     * @param criteria
     * @return
     */
    @Select
    Optional<User> select(UserCriteria criteria);

    /**
     * ユーザを1件取得します。(id)
     * @param id
     * @return
     */
    @Select
    Optional<User> selectById(Long id);

    /**
     * ユーザを1件取得します。(email)
     * @param email
     * @return
     */
    @Select
    Optional<User> selectByEmail(String email);

    @Insert
    @Transactional
    int insert(User user);


}

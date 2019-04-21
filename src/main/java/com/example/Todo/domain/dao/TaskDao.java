package com.example.Todo.domain.dao;

import com.example.Todo.domain.dto.Task;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collector;

@Dao
@ConfigAutowireable
public interface TaskDao {

    @Select(strategy = SelectType.COLLECT)//Collect検索 を使用する宣言
    <R> R selectAll(final Long userId, final SelectOptions options, final Collector<Task, ?, R> collector);
    //Daoメソッドの引数に Collector を渡すことで任意の型で結果を受け取ることができる
    //戻り値は任意型にしたいので R 、引数には Collector<Task, ?, R> を受け取るようにする
    //List＜Task＞ findAll();じゃいけないのか...コレだとList型しか受け取れない
    //呼び出しを下記のようにすれば年齢でMapした結果が受け取れる
    // Map＜category, List＜Task＞＞ TaskMap = TaskDao.findAll(Collectors.groupingBy(Task::getCategory));
    //List<Task>で受け取りたい場合はList＜Task＞ TaskList = TaskDao.findAll(Collectors.toList());

    @Insert
    @Transactional
    int insert(Task task);
}

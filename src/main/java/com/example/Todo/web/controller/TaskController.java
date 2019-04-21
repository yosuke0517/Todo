package com.example.Todo.web.controller;

import com.example.Todo.common.controller.BaseController;
import com.example.Todo.domain.dto.Task;
import com.example.Todo.security.LoginUser;
import com.example.Todo.service.TaskService;
import com.example.Todo.web.form.TaskForm;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class TaskController extends BaseController {

    @Autowired
    TaskService taskService;

    @ModelAttribute("taskForm")
    TaskForm taskForm(){return new TaskForm();}

    /**
     * Todoの新規投稿
     */
    //Todo あとでパラメータとかのためにコメント作り直し
    @RequestMapping("/task/create")
    @Transactional(readOnly=false)
    public String taskPost(@ModelAttribute("taskForm") TaskForm taskForm){

        // 入力値からDTOを作成する
        val inputTask = modelMapper.map(taskForm, Task.class);

        // 登録する
        val createdTask = taskService.createTask(inputTask);

        Long userId = taskForm.getUser_id();

        return "redirect:/show/"  + userId;
    }


    @GetMapping("/task/find")
    public String taskfind(@ModelAttribute("taskForm") TaskForm taskForm, Model model){

        //ページ情報を受け取り10件ずつ
        val pages = taskService.findAllTask(taskForm.getUser_id(),taskForm);

        //ユーザIDだけ別持ち
        val user_id = taskForm.getUser_id();

        model.addAttribute("tasks",pages);
        model.addAttribute("user_id",user_id);

        return "user/find";
    }

}

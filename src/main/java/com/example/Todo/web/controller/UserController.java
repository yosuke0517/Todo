package com.example.Todo.web.controller;

import com.example.Todo.common.controller.BaseController;
import com.example.Todo.domain.dao.UserDao;
import com.example.Todo.domain.dto.User;
import com.example.Todo.domain.dto.common.Pageable;
import com.example.Todo.domain.validation.UserFormValidator;
import com.example.Todo.service.TaskService;
import com.example.Todo.service.UserServiceImpl;
import com.example.Todo.web.form.UserForm;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class UserController extends BaseController {

    @Autowired
    UserFormValidator userFormValidator;

    @InitBinder("userForm")
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(userFormValidator);
    }

    @Autowired
    UserDao userDao;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    TaskService taskService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @ModelAttribute("userForm")
    UserForm userForm(){return new UserForm();}


    @GetMapping("/newUser")
    public String newUser(){

        return "user/new";
    }


    /**
     * ユーザ新規作成
     * @param userForm
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/user/create")
    public String createUser(@Validated @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

            // 入力チェックエラーがある場合は、元の画面にもどる
            if (bindingResult.hasErrors()) {
                return "redirect:/user/new";
            }
            // 入力値からDTOを作成する
            val inputUser = modelMapper.map(userForm, User.class);
            val password = userForm.getPassword();

            // パスワードをハッシュ化する
            inputUser.setPassword(passwordEncoder.encode(password));

            // 登録する
            val createdUser = userService.create(inputUser);

        //ログインしてる場合
        return "redirect:/show/" + createdUser.getId();
    }



    /**
     * 詳細画面
     *
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/show/{userId}")
    public String show(@ModelAttribute("userForm") UserForm userForm,@PathVariable Long userId, Model model){

        // 1件取得する
        val user = userService.findById(userId);
        String email = user.getEmail();
        model.addAttribute("user", user);
        model.addAttribute("loginEmail",email);

//        //Todo Task管理のページ別にする？
//        // 10件区切りで取得する
//        val page = taskService.findAllTask(userId,userForm);
//
//        //val tasks = taskService.findAllTask(userId,pageable);
//        model.addAttribute("tasks",page);


        //画像登録とかはまた今度
//        if (user.getUploadFile() != null) {
//            // 添付ファイルを取得する
//            val uploadFile = user.getUploadFile();
//
//            // Base64デコードして解凍する
//            val base64data = uploadFile.getContent().toBase64();
//            val sb = new StringBuilder().append("data:image/png;base64,").append(base64data);
//
//            model.addAttribute("image", sb.toString());
//        }

        return "user/show";
    }

}

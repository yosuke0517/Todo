package com.example.Todo.web.controller;

import com.example.Todo.common.controller.AbstractHtmlController;
import com.example.Todo.common.controller.BaseController;
import com.example.Todo.domain.dto.User;
import com.example.Todo.security.LoginUser;
import com.example.Todo.service.UserService;
import com.example.Todo.service.UserServiceImpl;
import com.example.Todo.web.form.LoginForm;
import com.mysql.cj.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.example.Todo.common.WebConst.*;

@Controller
public class LoginController extends AbstractHtmlController {

    @Autowired
    UserServiceImpl userService;

    @Override
    public String getFunctionName() {
        return "A_LOGIN";
    }

    @ModelAttribute
    LoginForm loginForm(){return new LoginForm();}
    /**
     * ログイン画面表示
     * @return getメソッドの時はログイン画面を表示する
     */
    @GetMapping("/loginForm")
    public String loginFrom(){

        return "login/login";
    }



    /**
     * 入力チェック
     *
     * @param form
     * @param br
     * @return
     */
    @PostMapping("/loginForm")
    public String index(@Validated @ModelAttribute LoginForm form, BindingResult br,
                        RedirectAttributes redirectAttributes) {
        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            return "login/login";
        }
        //20190309入力チェックが通った場合は、SecurityConfigで設定した認証処理にフォワードする
        //20190309Postメソッドでなければいけないのでforwardを使う必要がある
        return "forward:" + "/login";
    }

    /**
     * ログイン成功
     */
    @Scope("session")
    @PostMapping("/success")
    public String loginsuccess(@ModelAttribute LoginForm loginForm, RedirectAttributes redirectAttributes,
                               SecurityContextHolder securityContextHolder,Model model){

        //メールアドレスをログインIDと見立てる
        String loginEmail;
        User user;

        //認証情報の取得
        ////ナビバーへログインしているユーザのIDを表示させるための認証情報を取得する
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            loginEmail = ((UserDetails)principal).getUsername();
        } else {
            loginEmail = principal.toString();
        }

        //ユーザのIDを取得する
        user = userService.findByEmail(loginEmail);
        Long user_id = user.getId();

        //ユーザのemailをModelに詰める
        //redirectAttributes.addFlashAttribute("loginEmail",loginEmail);
        //ログインのグローバルメッセージを詰める
        redirectAttributes.addFlashAttribute(GLOBAL_MESSAGE, getMessage("login.success"));

        //マイページへ飛ばす
        return "redirect:/show/" + user_id;
    }

    /**
     * ログイン失敗
     */
    @GetMapping("/loginFailure")
    public String loginFailure(@ModelAttribute LoginForm loginForm,Model model){
        model.addAttribute(GLOBAL_MESSAGE,getMessage("login.failed"));
        return "/login/login";
    }

    /**
     * ログアウト成功
     */
    @GetMapping("/logoutSuccess")
    public String logout(@ModelAttribute LoginForm loginForm, Model model,RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(GLOBAL_MESSAGE, getMessage("logout.success"));
        /*
        20190309
        ログアウト処理が成功したら本メソッドで受け取る
         */
        return "redirect:/login";
    }

}

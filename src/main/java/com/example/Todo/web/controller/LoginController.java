package com.example.Todo.web.controller;

import com.example.Todo.common.controller.AbstractHtmlController;
import com.example.Todo.common.controller.BaseController;
import com.example.Todo.web.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

    @RequestMapping("/")
    public String index(Model model){
        return "home/success";
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
    @PostMapping("/success")
    public String loginsuccess(@ModelAttribute LoginForm loginForm, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute(GLOBAL_MESSAGE, getMessage("login.success"));

        return "redirect:/";
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

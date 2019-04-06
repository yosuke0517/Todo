package com.example.Todo;

import com.example.Todo.common.security.DefaultAccessDeniedHandler;
import com.example.Todo.common.security.DefaultAuthenticationEntryPoint;
import com.example.Todo.common.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

import static com.example.Todo.common.WebConst.*;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${application.security.rememberMe.cookieName:rememberMe}")
    String rememberMeCookieName;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //リメンバーミーキー（固定でいいらしい&なんでもいいらしい）
    private static final String REMEMBER_ME_KEY = "sampleRememberMeKey";


    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静的ファイルへのアクセスは認証をかけない
        web.ignoring()//
                .antMatchers(WEBJARS_URL, STATIC_RESOURCES_URL,CSS_URL,JS_URL,IMAGE_URL);
    }

    /**
     * UserDetailsServiceインターフェースを実装した独自の認証レルムを使用する設定
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // CookieにCSRFトークンを保存する
        http.csrf()//
                .csrfTokenRepository(new CookieCsrfTokenRepository());

        http.authorizeRequests()
                .antMatchers("/loginForm").permitAll()
                .antMatchers("/loginFailure").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/newUser").permitAll()//test用(ユーザ登録)
                .antMatchers("/index").permitAll()//test用(ユーザ登録後の遷移画面）
//                .antMatchers("/user/create").permitAll()//test用機能
//                .antMatchers("/user/new").permitAll()//ユーザ作成
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());
        //ログイン設定
        http.formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .failureUrl("/loginFailure")
                .successForwardUrl("/success")
                .usernameParameter("email")
                .passwordParameter("password");
        // ログアウト設定
        http.logout()//
                .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
                // Cookieを破棄する
                .deleteCookies("SESSION", "JSESSIONID", rememberMeCookieName)
                // ログアウト画面のURL
                .logoutUrl(LOGOUT_URL)
                // ログアウト後の遷移先
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL)
                // ajaxの場合は、HTTPステータスを返す
                .defaultLogoutSuccessHandlerFor(new HttpStatusReturningLogoutSuccessHandler(),
                        RequestUtils::isAjaxRequest)
                // セッションを破棄する
                .invalidateHttpSession(true).permitAll();

        // RememberMeとりあえず放置
        //http.rememberMe().key(REMEMBER_ME_KEY)//
          //      .rememberMeServices(multiDeviceRememberMeServices());

    }


    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new DefaultAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        // APIがアクセス拒否された時の例外を返す
        //認証に失敗し403を返す時にリダイレクトしたりできる。
        return new DefaultAuthenticationEntryPoint("/loginForm", LOGIN_TIMEOUT_URL);
    }



}

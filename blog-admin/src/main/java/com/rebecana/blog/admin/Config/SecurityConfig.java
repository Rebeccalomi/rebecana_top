package com.rebecana.blog.admin.Config;

import com.rebecana.blog.admin.filter.MyAuthenticationFailureHandler;
import com.rebecana.blog.admin.filter.VerificationCodeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//继承自一个抽象的配置类，下面的方法都是重写
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    //密码加密策略，比较安全，可以自动加密、匹配
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        //可以对应
        return new BCryptPasswordEncoder();
    }

    //可以生成密码进入数据库
    public static void main(String[] args) {
        //加密策略 MD5 不安全 彩虹表  MD5 加盐
        String mszlu = new BCryptPasswordEncoder().encode("1193479622");
        System.out.println(mszlu);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests() //开启登录认证
//                .antMatchers("/user/findAll").hasRole("admin") //访问接口需要admin的角色
                .antMatchers("/css/**").permitAll() //permitall放行
                .antMatchers("/img/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/captcha.jpg").permitAll()
                .antMatchers("/plugins/**").permitAll()
                .antMatchers("/admin/**").access("@authService.auth(request,authentication)") //自定义service 来去实现实时的权限认证
                .antMatchers("/pages/**").authenticated()  //登录成功就可以
                .and().formLogin()
                .loginPage("/") //自定义的登录页面
                .loginProcessingUrl("/login") //登录处理接口
                .failureHandler(new MyAuthenticationFailureHandler())
                .usernameParameter("username") //定义登录时的用户名的key 默认为username
                .passwordParameter("password") //定义登录时的密码key，默认是password
                .defaultSuccessUrl("/pages/main.html")
                .failureUrl("/"+"?key="+"PasswordError")
                .permitAll() //通过 不拦截，更加前面配的路径决定，这是指和登录表单相关的接口 都通过
                .and().logout() //退出登录配置
                .logoutUrl("/logout") //退出登录接口
                .logoutSuccessUrl("/")
                .permitAll() //退出登录的接口放行
                .and()
                .httpBasic()
                .and()
                .csrf().disable() //csrf关闭 如果自定义登录 需要关闭
                .headers().frameOptions().sameOrigin();// 支持iframe页面嵌套

        http.addFilterBefore(new VerificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

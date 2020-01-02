package com.example.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author uglys
 * Create on 2020/1/2 at 11:08
 * TODO
 */
@Controller
public class MainController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/do-login")
    public String doLogin(Model model, String username, String password) {
        System.out.println("Username: " + username);
        /*
         * 使用Shiro编写认证操作
         */
        // 1.获取Subject
        Subject subject = SecurityUtils.getSubject();

        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 3.执行登录方法
        try {
            subject.login(token);

            return "redirect:/user/";
        } catch (UnknownAccountException e) {
            // 登录失败
            // e.printStackTrace();
            System.out.println("用户名不存在");
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            // e.printStackTrace();
            System.out.println("密码错误");
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }
}

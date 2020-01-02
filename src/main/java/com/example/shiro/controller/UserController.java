package com.example.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author uglys
 * Create on 2019/12/31 at 15:44
 * TODO
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "/")
    public String userIndex(Model model) {
        model.addAttribute("name", "Java");
        return "user/index";
    }

    @GetMapping(value = "/add")
    public String addUser() {
        return "user/add";
    }

    @GetMapping(value = "/update")
    public String updateUser() {
        return "user/update";
    }

}

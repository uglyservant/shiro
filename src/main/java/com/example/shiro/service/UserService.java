package com.example.shiro.service;

import com.example.shiro.entity.User;
import com.example.shiro.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author uglys
 * Create on 2020/1/2 at 14:40
 * TODO
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    public String findPermsByUserId(Integer id) {
        return userMapper.findPermsByUserId(id);
    }
}

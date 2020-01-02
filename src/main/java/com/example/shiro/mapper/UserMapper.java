package com.example.shiro.mapper;

import com.example.shiro.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author uglys
 * Create on 2020/1/2 at 14:20
 * TODO
 */
@Repository
public interface UserMapper {

    User findUserByUsername(String username);

    String findPermsByUserId(Integer id);
}

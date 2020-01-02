package com.example.shiro.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author uglys
 * Create on 2020/1/2 at 14:20
 * TODO
 */
@Getter
@Setter
@Accessors(chain = true)
public class User {
    private Integer id;
    private String username;
    private String password;
    private String perms;
}

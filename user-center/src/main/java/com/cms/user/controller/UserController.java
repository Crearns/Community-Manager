package com.cms.user.controller;

import com.cms.common.entity.User;
import com.cms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Creams
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("user-auth")
    public User selectByUsername(String username) {
        if (username == null || username.trim().equals("")){
            return null;
        }
        return userService.selectByUserId(username);
    }

}

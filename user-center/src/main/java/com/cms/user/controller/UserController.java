package com.cms.user.controller;

import com.cms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Creams
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

}

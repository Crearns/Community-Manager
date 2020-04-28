package com.cms.user.controller;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.User;
import com.cms.common.query.UserQuery;
import com.cms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Creams
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/num/user")
    public ServerResponse<User> userNum(@RequestParam("userNum") Long userNum) {
        UserQuery userQuery = new UserQuery();
        userQuery.setUserNum(userNum);
        return ServerResponse.createSuccessResponse(userService.query(userQuery).get(0));
    }


    @GetMapping("/id/user")
    public ServerResponse<User> userId(@RequestParam("userId") Long userId) {
        UserQuery userQuery = new UserQuery();
        userQuery.setId(userId);
        return ServerResponse.createSuccessResponse(userService.query(userQuery).get(0));
    }

}

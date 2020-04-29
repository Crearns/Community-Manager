package com.cms.web.controller;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.User;
import com.cms.web.feign.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Creams
 */

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserClient userClient;

    @PutMapping("/info")
    public ServerResponse info(@RequestParam("userId") Long userId,
                               Long phone,
                               String email,
                               String nickname) {

        log.info(userId.toString());
        User user = new User();
        user.setId(userId);
        user.setPhone(phone);
        user.setNickname(nickname);
        user.setEmail(email);
        return userClient.info(user);
    }

    @PutMapping("/password")
    public ServerResponse password(@RequestParam("userId") Long userId,
                            @RequestParam("passwordOld") String passwordOld,
                            @RequestParam("passwordNew") String passwordNew) {
        return userClient.password(userId, passwordOld, passwordNew);
    }
}

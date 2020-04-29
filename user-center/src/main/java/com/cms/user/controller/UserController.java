package com.cms.user.controller;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.User;
import com.cms.common.query.UserQuery;
import com.cms.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Creams
 */
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @PutMapping(value = "/info")
    public ServerResponse info(@RequestBody User user) {
        if (user == null || user.getId() == null) {
            return ServerResponse.createFailureResponse("找不到修改用户的id");
        }
        return ServerResponse.createSuccessResponse(userService.updateCommonInfo(user));
    }

    @PutMapping("password")
    public ServerResponse password(@RequestParam("userId") Long userId,
                                   @RequestParam("passwordOld") String passwordOld,
                                   @RequestParam("passwordNew") String passwordNew) {
        UserQuery userQuery = new UserQuery();
        userQuery.setId(userId);

        User user = userService.query(userQuery).get(0);

        if (user == null) {
            log.error("Current user not found id:{}", userId);
            return ServerResponse.createFailureResponse("找不到当前用户");
        }

        log.info(user.getPassword());
        log.info(passwordEncoder.encode(passwordOld));

        if (!passwordEncoder.matches(passwordOld, user.getPassword())) {
            return ServerResponse.createFailureResponse("密码错误，请确认后重试");
        }

        if (passwordEncoder.matches(passwordNew, user.getPassword())) {
            return ServerResponse.createFailureResponse("新旧密码不能一致");
        }

        User update = new User();
        update.setId(userId);
        update.setPassword(passwordEncoder.encode(passwordNew));

        return ServerResponse.createSuccessResponse(userService.update(update));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

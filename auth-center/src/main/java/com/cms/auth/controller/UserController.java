package com.cms.auth.controller;

import com.cms.common.common.ServerResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * @author Creams
 */
@Controller
public class UserController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("user/current")
    @ResponseBody
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/getUser")
    @ResponseBody
    public ServerResponse getUser() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        if (StringUtils.isBlank(user)) {
            return ServerResponse.createFailureResponse("User not found");
        } else {
            return ServerResponse.createSuccessResponse(user);
        }
    }

}

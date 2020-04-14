package com.cms.auth.controller;

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
    public String getUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}

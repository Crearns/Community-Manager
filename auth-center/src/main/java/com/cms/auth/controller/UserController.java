package com.cms.auth.controller;

import com.cms.common.common.ServerResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Creams
 */
@RestController
public class UserController {

    @GetMapping("user/me")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/user/userId")
    public String getUser() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println(user);

        return user;
    }
}

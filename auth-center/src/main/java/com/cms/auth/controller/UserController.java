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

import java.security.Principal;

/**
 * @author Creams
 */
@Controller
public class UserController {
    @Qualifier("consumerTokenServices")
    @Autowired
    private ConsumerTokenServices tokenServices;

    @GetMapping("user/me")
    @ResponseBody
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/getUser")
    @ResponseBody
    public ServerResponse<String> getUser() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        if (StringUtils.isBlank(user)) {
            return ServerResponse.createFailureResponse("User not found");
        } else {
            return ServerResponse.createSuccessResponse(user);
        }
    }

    @DeleteMapping(value = "/removeToken", params = {"accessToken"})
    @ResponseBody
    public void removeToken(String accessToken) {
        tokenServices.revokeToken(accessToken);
    }
}

package com.cms.community.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * @author Creams
 */
@FeignClient("auth-server")
public interface OauthClient {

    @GetMapping("uaa/user/me")
    Principal user(Principal principal);

}


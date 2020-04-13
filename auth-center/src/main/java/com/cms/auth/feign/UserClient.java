package com.cms.auth.feign;

import com.cms.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Creams
 */

@FeignClient("user-service")
public interface UserClient {

    @GetMapping(value = "/user/user-auth", params = "username")
    User findByUsername(@RequestParam("username") String username);
}

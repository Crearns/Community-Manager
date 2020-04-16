package com.cms.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Creams
 */
@FeignClient("auth-server")
public interface AuthClient {

    @PostMapping("uaa/oauth/token")
    Map<String, Object> postAccessToken(@RequestParam Map<String, String> parameters);

    @DeleteMapping(path = "/uaa/removeToken")
    void removeToken(@RequestParam("accessToken") String accessToken);
}

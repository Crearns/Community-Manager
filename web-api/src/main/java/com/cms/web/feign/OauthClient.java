package com.cms.web.feign;

import com.cms.common.common.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Creams
 */

@FeignClient("auth-server")
public interface OauthClient {

    @GetMapping("uaa/user/userId")
    String userId();

}

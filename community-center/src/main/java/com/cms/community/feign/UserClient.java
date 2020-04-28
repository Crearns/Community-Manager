package com.cms.community.feign;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Creams
 */

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/num/user")
    ServerResponse<User> userNum(@RequestParam("userNum") Long userNum);

    @GetMapping("/user/id/user")
    ServerResponse<User> userId(@RequestParam("userId") Long userId);

}

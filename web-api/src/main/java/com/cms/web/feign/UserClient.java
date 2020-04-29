package com.cms.web.feign;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Creams
 */

@FeignClient("user-service")
public interface UserClient {

    @PutMapping("/user/info")
    ServerResponse info(@RequestBody User user);

    @PutMapping("/user/password")
    ServerResponse password(@RequestParam("userId") Long userId,
                                   @RequestParam("passwordOld") String passwordOld,
                                   @RequestParam("passwordNew") String passwordNew);
}

package com.cms.web.feign;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Creams
 */

@FeignClient("message-push-service")
public interface MessageClient {

    @GetMapping("/message/message")
    ServerResponse<List<Message>> getMessage(@RequestParam("userId") Integer userId);

    @PutMapping("/message/allReadMark")
    ServerResponse readAll(@RequestParam("userId") Long userId);
}

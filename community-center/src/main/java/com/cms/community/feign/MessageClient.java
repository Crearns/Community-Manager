package com.cms.community.feign;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Creams
 */
@FeignClient("message-push-service")
public interface MessageClient {

    @PostMapping("/message/message")
    ServerResponse<Message> send(@RequestParam("receiveId") Long receiveId,
                                        @RequestParam("title") String title,
                                        @RequestParam("content") String content);

    @PostMapping("/message/messageAll")
    ServerResponse sendAll(@RequestParam("receiveIds") List<Long> receiveIds,
                           @RequestParam("title") String title,
                           @RequestParam("content") String content);
}

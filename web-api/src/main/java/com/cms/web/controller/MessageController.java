package com.cms.web.controller;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Message;
import com.cms.web.feign.MessageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Creams
 */

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageClient messageClient;


    @GetMapping("/message")
    public ServerResponse<List<Message>> message(@RequestParam("userId") Integer userId) {
        return messageClient.getMessage(userId);
    }

    @PutMapping("/allReadMark")
    public ServerResponse readAll(@RequestParam("userId") Long userId) {
        messageClient.readAll(userId);
        return ServerResponse.createSuccessResponse();
    }
}

package com.cms.message.controller;

import com.alibaba.fastjson.JSON;
import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Message;
import com.cms.message.server.WebSocketServer;
import com.cms.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Creams
 */
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private WebSocketServer webSocketServer;

    @PostMapping("message")
    public ServerResponse<Message> send(@RequestParam("receiveId") Long receiveId,
                                        @RequestParam("title") String title,
                                        @RequestParam("content") String content) {
        Message message = new Message();
        message.setGmtCreate(new Date());
        message.setContent(content);
        message.setReceiverId(receiveId);
        message.setTitle(title);
        messageService.saveMessage(message);
        webSocketServer.sendOneMessage(receiveId, JSON.toJSONString(ServerResponse.createSuccessResponse(messageService.getUnReadCount(receiveId))));
        return ServerResponse.createSuccessResponse(message);
    }

    @PutMapping("readMark")
    public ServerResponse<String> read(@RequestParam("id") String messageId) {
        messageService.readMark(messageId);
        return ServerResponse.createSuccessResponse(messageId);
    }

    @GetMapping("message")
    public ServerResponse<List<Message>> message(@RequestParam("userId") Long userId) {
        return ServerResponse.createSuccessResponse(messageService.findMessageByRecId(userId));
    }

    @GetMapping("unReadCount")
    public ServerResponse<Integer> unReadCount(@RequestParam("userId") Long userId) {
        return ServerResponse.createSuccessResponse(messageService.getUnReadCount(userId));
    }

    @PutMapping("allReadMark")
    public ServerResponse readAll(@RequestParam("userId") Long userId) {
        messageService.readAll(userId);
        return ServerResponse.createSuccessResponse();
    }

}

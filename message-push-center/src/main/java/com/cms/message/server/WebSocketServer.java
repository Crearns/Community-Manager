package com.cms.message.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.cms.common.common.ServerResponse;
import com.cms.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/webSocket/{userId}")
public class WebSocketServer {
    private static ApplicationContext applicationContext;

    private Session session;

    private static CopyOnWriteArraySet<WebSocketServer> webSockets =new CopyOnWriteArraySet<>();
    private static Map<Long,Session> sessionPool = new HashMap<Long,Session>();


    @OnOpen
    public void onOpen(Session session, @PathParam(value="userId")Long userId) {
        MessageService messageService = applicationContext.getBean(MessageService.class);
        this.session = session;
        webSockets.add(this);
        sessionPool.put(userId, session);
        session.getAsyncRemote().sendText(JSON.toJSONString(ServerResponse.createSuccessResponse(messageService.getUnReadCount(userId))));
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
    }

    @OnMessage
    public void onMessage(String message) {
    }

    public void sendAllMessage(String message) {
        for(WebSocketServer webSocket : webSockets) {
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendOneMessage(Long userId, String message) {
        Session session = sessionPool.get(userId);
        if (session != null && session.isOpen()) {
            try {
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

}
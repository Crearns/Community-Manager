package com.cms.message.server;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Creams
 */

@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        sendMessage("连接成功");
    }


    public void sendMessage(Object message) {
        try {
            this.session.getBasicRemote().sendText(JSON.toJSONString(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现服务器主动群发消息
     */
    public static void sendInfo(Object message) {
        for (WebSocketServer item : webSocketSet) {
            item.sendMessage(message);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param queryType 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String queryType) {
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            item.sendMessage("hello");
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
}

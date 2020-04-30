package com.cms.message;

import com.cms.message.server.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author Creams
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MessageApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MessageApplication.class, args);
        WebSocketServer.setApplicationContext(applicationContext);

    }
}

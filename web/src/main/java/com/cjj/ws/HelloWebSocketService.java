package com.cjj.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author cjj
 * @date 2022/9/26 10:11
 * @description
 **/
@Component
@ServerEndpoint("/hello/{userId}")
@Slf4j
public class HelloWebSocketService {

    private static Map<String, Session> clients = new ConcurrentHashMap<>();
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(8);


    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        log.info("客户：{}连接成功", userId);

        clients.put(userId, session);

        HelloWebSocketTask task = new HelloWebSocketTask();
        scheduledExecutorService.scheduleAtFixedRate(task, 1, 10, TimeUnit.SECONDS);
    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        log.info("客户端：{}连接断开", session.getId());

        clients.remove(userId);

    }

    @OnMessage
    public void onMsg(@PathParam("userId") String userId, String message, Session session) {
        sendMessage(userId, message, session);

    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("user id:" + session.getId() + ",reason:" + error.getMessage());
        error.printStackTrace();
    }

    public void sendMessage(String userId, String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息[{}]", userId, message);
            toSession.getAsyncRemote().sendText("客户：" + userId + "," + message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端{}失败：{}", userId, e);
        }
    }


    class HelloWebSocketTask implements Runnable {

        @Override
        public void run() {
            clients.keySet().stream().forEach(userId -> {
                Session toSession = clients.get(userId);
                sendMessage(userId, "服务器的定时推送", toSession);
            });
        }
    }

}

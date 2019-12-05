package com.szzt.iot.api.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zhouhongjin
 */
@Slf4j
public class ApiWebSocketHandler extends TextWebSocketHandler {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static volatile int onlineCount = 0;
    /**
     * WebSocketSession 的sessionId 与 userId 的对应关系 map 集合
     */
    private static final ConcurrentMap<String, Long> sessionIdToUserIdMap = new ConcurrentHashMap<>();
    /**
     * userId 对应的 WebSocketSession map集合
     */
    private static final ConcurrentMap<Long, WebSocketSession> userSessionsMap = new ConcurrentHashMap<>();


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("接收到客户端发送过来的消息：[{}]", message.getPayload());
        TextMessage textMessage = new TextMessage("服务端回复消息" + new Date() + "-" + message.getPayload());
        try {
            session.sendMessage(textMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        Long userId = (Long) session.getAttributes().get("userId");
        addOnlineCount();
        userSessionsMap.put(userId, session);
        sessionIdToUserIdMap.put(session.getId(), userId);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable tError) throws Exception {
        super.handleTransportError(session, tError);
        log.trace("[{}] Session transport error", session.getId(), tError);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        super.afterConnectionClosed(session, closeStatus);
        subOnlineCount();
        Long userId = sessionIdToUserIdMap.get(session.getId());
        sessionIdToUserIdMap.remove(session.getId());
        userSessionsMap.remove(userId);
        log.info("[{}] Session is closed", session.getId());
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ApiWebSocketHandler.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ApiWebSocketHandler.onlineCount--;
    }

    /**
     * 实现服务器主动推送
     */
    public static void sendMessage(Long userId, String message) throws IOException {
        WebSocketSession webSocketSession = userSessionsMap.get(userId);
        TextMessage textMessage = new TextMessage(message.getBytes());
        webSocketSession.sendMessage(textMessage);
    }

}
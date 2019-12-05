package com.szzt.iot.api.config;

import com.szzt.iot.api.entity.TokenEntity;
import com.szzt.iot.api.service.TokenService;
import com.szzt.iot.api.websocket.ApiWebSocketHandler;
import com.szzt.iot.common.exception.ErrorCode;
import com.szzt.iot.common.exception.IotException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * WebSocket 配置 开启WebSocket支持
 *
 * @author zhouhongjin
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    public static final String WS_PLUGIN_PREFIX = "/ws/";
    public static final String WS_PLUGIN_MAPPING = WS_PLUGIN_PREFIX + "**";

    @Autowired
    private TokenService tokenService;

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(32768);
        container.setMaxBinaryMessageBufferSize(32768);
        return container;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsHandler(), WS_PLUGIN_MAPPING).setAllowedOrigins("*")
                .addInterceptors(new HttpSessionHandshakeInterceptor(), new HandshakeInterceptor() {

                    @Override
                    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                                   Map<String, Object> attributes) throws Exception {
                        //从header中获取token
                        String token = ((ServletServerHttpRequest) request).getServletRequest().getParameter("token");
//                        //如果header中不存在token，则从参数中获取token
//                        if(StringUtils.isBlank(token)){
//                            token = httpServletRequest.getParameter("token");
//                        }

                        //token为空
                        if(StringUtils.isBlank(token)){
                            throw new IotException(ErrorCode.TOKEN_NOT_EMPTY);
                        }

                        //查询token信息
                        TokenEntity tokenEntity = tokenService.getByToken(token);
                        if(tokenEntity == null || tokenEntity.getExpireDate().getTime() < System.currentTimeMillis()){
                            throw new IotException(ErrorCode.TOKEN_INVALID);
                        }

                        // 通过attributes 设置 WebSocketSession 属性
                        attributes.put("userId", tokenEntity.getUserId());
                        if (tokenEntity == null || tokenEntity.getUserId() == null) {
                            response.setStatusCode(HttpStatus.UNAUTHORIZED);
                            return false;
                        } else {
                            return true;
                        }
                    }

                    @Override
                    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                               Exception exception) {
                        //Do nothing
                    }
                });
    }

    @Bean
    public WebSocketHandler wsHandler() {
        return new ApiWebSocketHandler();
    }


}

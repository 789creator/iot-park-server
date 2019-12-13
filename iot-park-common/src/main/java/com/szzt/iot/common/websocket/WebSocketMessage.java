package com.szzt.iot.common.websocket;

import lombok.Data;

import java.io.Serializable;

/**
 * websocket 消息
 *
 * @author zhouhongjin
 */
@Data
public class WebSocketMessage<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 消息头
     */
    private MessageHeader messageHeader;
    /**
     * 消息体
     */
    private T messageBody;
}

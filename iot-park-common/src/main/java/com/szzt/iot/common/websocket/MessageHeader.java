package com.szzt.iot.common.websocket;

import lombok.Data;

import java.io.Serializable;

/**
 * websocket 消息头
 *
 * @author zhouhongjin
 */
@Data
public class MessageHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    private int actionCode;

    /**
     * 消息发送时间 yyyy-mm-dd hh:mm:ss
     */
    private String sendTime;
}

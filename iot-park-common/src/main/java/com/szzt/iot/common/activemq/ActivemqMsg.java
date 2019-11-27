package com.szzt.iot.common.activemq;

import lombok.Data;

import java.io.Serializable;

/**
 * activemq 消息
 *
 * @author zhouhongjin
 */
@Data
public class ActivemqMsg<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 消息头
     */
    private MsgHeader msgHeader;
    /**
     * 消息体
     */
    private T msgBody;
}

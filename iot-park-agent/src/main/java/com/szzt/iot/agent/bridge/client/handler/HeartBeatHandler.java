package com.szzt.iot.agent.bridge.client.handler;

import com.google.protobuf.MessageLite;
import com.szzt.iot.common.netty.IMHeader;
import io.netty.channel.ChannelHandlerContext;

/**
 * 客户端心跳 handler
 *
 * @author zhouhongjin
 */
public interface HeartBeatHandler {
    /**
     * 客户端接收到服务端的心跳信息处理
     *
     * @param header
     * @param body
     * @param ctx
     */
    void doHeartBeat(IMHeader header, MessageLite body, ChannelHandlerContext ctx);
}

package com.szzt.iot.admin.modules.netty.server.handler;

import com.google.protobuf.MessageLite;
import com.szzt.iot.common.netty.IMHeader;
import io.netty.channel.ChannelHandlerContext;

/**
 * 心跳 handler
 *
 * @author zhouhongjin
 */
public interface HeartBeatHandler {
    /**
     * 服务端接收到客户端的心跳信息处理
     *
     * @param header
     * @param body
     * @param ctx
     */
    void doHeartBeat(IMHeader header, MessageLite body, ChannelHandlerContext ctx);
}

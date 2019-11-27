package com.szzt.iot.admin.modules.netty.server.handler.impl;

import com.google.protobuf.MessageLite;
import com.szzt.iot.admin.modules.netty.server.manager.ClientConnectionInfo;
import com.szzt.iot.admin.modules.netty.server.manager.ClientConnectionMap;
import com.szzt.iot.common.netty.IMHeader;
import com.szzt.iot.admin.modules.netty.server.handler.HeartBeatHandler;
import com.szzt.iot.common.netty.proto.IMHeartBeat;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 心跳 handler 实现类
 *
 * @author zhouhongjin
 */
@Slf4j
public class HeartBeatHandlerImpl implements HeartBeatHandler {
    @Override
    public void doHeartBeat(IMHeader header, MessageLite body, ChannelHandlerContext ctx) {
        log.info("=============服务端接收到客户端的心跳=============");
        IMHeartBeat.IMHeartBeatData msgdata = (IMHeartBeat.IMHeartBeatData) body;
        ClientConnectionInfo clientConnectionInfo = ClientConnectionMap.connectionMap.get(msgdata.getChannelId().toStringUtf8());
        if (clientConnectionInfo != null) {
            clientConnectionInfo.setHeartBeatCheckNum(0);
        }
    }
}

package com.szzt.iot.agent.bridge.client.handler.impl;

import com.google.protobuf.MessageLite;
import com.szzt.iot.agent.bridge.client.handler.HeartBeatHandler;
import com.szzt.iot.common.netty.DefaultIMHeader;
import com.szzt.iot.common.netty.IMHeader;
import com.szzt.iot.common.netty.IMProtoMessage;
import com.szzt.iot.common.netty.proto.IMBaseDefine;
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
        System.out.println("============客户端收到心跳============");
        IMHeartBeat.IMHeartBeatData msgdata = (IMHeartBeat.IMHeartBeatData) body;
        // 发送心跳消息
        IMHeartBeat.IMHeartBeatData.Builder builder = IMHeartBeat.IMHeartBeatData.newBuilder();

        builder.setChannelId(msgdata.getChannelId());
        IMHeader heartBeatHeader = new DefaultIMHeader(IMBaseDefine.ServiceID.SID_HEART_BEAT_VALUE, IMBaseDefine.HeartBeat.CID_HEART_BEAT_DEFAULT_VALUE);
        ctx.writeAndFlush(new IMProtoMessage<IMHeartBeat.IMHeartBeatData>(heartBeatHeader, builder.build()));
//        HeartBeatCheck.response(msgdata.getChannelId().toStringUtf8(), ctx);
        System.out.println("============客户端回复心跳============");
    }
}

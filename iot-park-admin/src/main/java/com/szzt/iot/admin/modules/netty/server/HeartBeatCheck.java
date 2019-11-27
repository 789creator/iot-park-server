package com.szzt.iot.admin.modules.netty.server;

import com.google.protobuf.ByteString;
import com.szzt.iot.common.netty.DefaultIMHeader;
import com.szzt.iot.common.netty.IMHeader;
import com.szzt.iot.common.netty.IMProtoMessage;
import com.szzt.iot.common.netty.proto.IMBaseDefine;
import com.szzt.iot.common.netty.proto.IMHeartBeat;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

/**
 * 心跳检测
 *
 * @author zhouhongjin
 */
@Slf4j
public class HeartBeatCheck {

    /**
     * 发送心跳信息
     *
     * @param ctx
     */
    public static void sendHeartBeatMsg(String id, ChannelHandlerContext ctx) {
        log.info("=============服务端发送心跳=============");
        log.info("id:"+id);
        send(id, ctx);
    }

    /**
     * 心跳回复
     *
     * @param ctx
     */
    public static void response(String id, ChannelHandlerContext ctx) {
        log.info("=============客户端"+ctx.channel().remoteAddress().toString()+"心跳回复心跳=============");
        log.info("id:"+id);
        send(id, ctx);
    }

    private static void send(String id, ChannelHandlerContext ctx) {
        // 发送心跳消息
        IMHeartBeat.IMHeartBeatData.Builder builder = IMHeartBeat.IMHeartBeatData.newBuilder();

        try {
            builder.setChannelId(ByteString.copyFrom(id.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        IMHeader header = new DefaultIMHeader(IMBaseDefine.ServiceID.SID_HEART_BEAT_VALUE, IMBaseDefine.HeartBeat.CID_HEART_BEAT_DEFAULT_VALUE);
        ctx.writeAndFlush(new IMProtoMessage<IMHeartBeat.IMHeartBeatData>(header, builder.build()));
    }

}

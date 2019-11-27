package com.szzt.iot.admin.modules.netty.server.handler;

import com.google.protobuf.MessageLite;
import com.szzt.iot.common.netty.IMHeader;
import com.szzt.iot.common.netty.IMProtoMessage;
import com.szzt.iot.common.netty.proto.IMBaseDefine;
import com.szzt.iot.admin.modules.netty.server.manager.ServerHandlerManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 处理客户端发来的消息
 *
 * @author zhouhongjin
 */
@Slf4j
public class MessageServerHandler extends ChannelInboundHandlerAdapter {

    private final ServerHandlerManager serverHandlerManager;

    public MessageServerHandler(ServerHandlerManager handlerManager) {
        this.serverHandlerManager = handlerManager;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        System.out.println("服务端："+ctx.channel().remoteAddress().toString()+"上线");
        serverHandlerManager.add(ctx);

        log.debug("handlerAdded");
    }

    /**
     * 每当服务端断开客户端连接时,客户端的channel从ChannelGroup中移除,并通知列表中其他客户端channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.debug("handlerRemoved");
        serverHandlerManager.remove(ctx);
        super.handlerRemoved(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 暂时只支持一次接收一个完整的消息
        // 如需要处理组合消息（长消息分多次发），需要另外处理，特别是解码逻辑
        IMProtoMessage<MessageLite> message = (IMProtoMessage<MessageLite>) msg;

        // ctx.fireChannelReadComplete();
        IMHeader header = message.getHeader();

        // 处理请求分发
        switch (header.getServiceId()) {
            case IMBaseDefine.ServiceID.SID_HEART_BEAT_VALUE:
                serverHandlerManager.doHeartBeat(ctx, header.getCommandId(), header, message.getBody());
                break;
            case IMBaseDefine.ServiceID.SID_MSG_VALUE:
                serverHandlerManager.doMessage(ctx, header.getCommandId(), header, message.getBody());
                break;
            case IMBaseDefine.ServiceID.SID_OTHER_VALUE:
                serverHandlerManager.doTest(ctx, header.getCommandId(), header, message.getBody());
                break;
            case IMBaseDefine.ServiceID.SID_ACTION_VALUE:
                serverHandlerManager.doAction(ctx, header.getCommandId(), header, message.getBody());
                break;
            default:
                log.warn("暂不支持的服务ID{}", header.getServiceId());
                break;
        }
    }

    /**
     * 服务端监听到客户端活动
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 服务端接收到客户端上线通知
        Channel incoming = ctx.channel();
        log.debug("MessageServerHandler:" + incoming.remoteAddress() + "在线");
        serverHandlerManager.online(ctx);
        ctx.fireChannelActive();
    }

    /**
     * 服务端监听到客户端不活动
     *
     * @param ctx 连接context
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 服务端接收到客户端掉线通知
        Channel incoming = ctx.channel();
        log.debug("MessageServerHandler:" + incoming.remoteAddress() + "掉线");
        serverHandlerManager.offline(ctx);
        ctx.fireChannelInactive();
    }

    /**
     * 当服务端的IO 抛出异常时被调用
     *
     * @param ctx   连接context
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        Channel incoming = ctx.channel();
        log.error("MessageServerHandler异常:{}", incoming.remoteAddress());
        // 异常出现
        ctx.fireExceptionCaught(cause);
    }
}

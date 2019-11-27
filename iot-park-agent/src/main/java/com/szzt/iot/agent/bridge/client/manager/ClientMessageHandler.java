package com.szzt.iot.agent.bridge.client.manager;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.szzt.iot.agent.bridge.client.RunClient;
import com.szzt.iot.agent.bridge.client.handler.impl.ClientScriptHandlerImpl;
import com.szzt.iot.common.netty.DefaultIMHeader;
import com.szzt.iot.common.netty.IMHeader;
import com.szzt.iot.common.netty.IMProtoMessage;
import com.szzt.iot.common.netty.proto.IMBaseDefine;
import com.szzt.iot.common.netty.proto.IMMessage;
import com.szzt.iot.common.utils.CommonUtils;
import com.szzt.iot.common.utils.SecurityUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

@Slf4j
public class ClientMessageHandler extends ChannelInboundHandlerAdapter {

    private static boolean reConnectioned = false;
//    private static int reConnectTimes = 0;
    /**
     * 重连次数阈值
     */
    private int CONNECTION_TIMES_LIMIT = 5;
    private final ClientHandlerManager clientHandlerManager;

    public ClientMessageHandler(ClientHandlerManager clientHandlerManager) {
        this.clientHandlerManager = clientHandlerManager;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 重新连接之后，重置重连次数

        if (RunClient.connectionTimes != 0) {
            RunClient.connectionTimes = 0;
            reConnectioned = true;
            System.out.println("重连成功");
        }
        super.channelActive(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        super.channelInactive(ctx);
        System.out.println("======channelInactive======");

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i <= CONNECTION_TIMES_LIMIT) {
                    i++;
                    if (!reConnectioned) {
                        // 等待任务执行完成之后停止程序
                        if (RunClient.connectionTimes == CONNECTION_TIMES_LIMIT) {
                            ClientScriptHandlerImpl.stopProgram = true;
                            if (!ClientScriptHandlerImpl.isRuningScript) {
                                System.exit(-1);
                            }
                        }
                        try {
                            System.out.println("第" + (RunClient.connectionTimes + 1) + "次重连");
                            RunClient runClient = RunClient.getInstance();
                            runClient.reConnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(1000 * 60 * 2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // 重置
                        reConnectioned = false;
                        break;
                    }
                }
            }
        }).start();
    }

    /**
     * 每当服务端断开客户端连接时,客户端的channel从ChannelGroup中移除,并通知列表中其他客户端channel
     *
     * @param ctx 连接context
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        // 1. 已经与远程主机建立的连接，远程主机主动关闭连接，或者网络异常连接被断开的情况
        // 2. 已经与远程主机建立的连接，本地客户机主动关闭连接的情况
        // 3. 本地客户机在试图与远程主机建立连接时，遇到类似与connection refused这样的异常，未能连接成功时
        // 而只有当本地客户机已经成功的与远程主机建立连接（connected）时，连接断开的时候才会触发channelDisconnected事件，即对应上述的1和2两种情况。

        super.handlerRemoved(ctx);
        System.out.println("======handlerRemoved======");


    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //重连，服务器停止不能触发这里的操作
        if (evt instanceof IdleStateEvent) {
//            RunClient runClient = (RunClient) SpringContextUtils.getBean("runClient");
            RunClient runClient = RunClient.getInstance();
            if (RunClient.connectionTimes <= CONNECTION_TIMES_LIMIT) {
                log.error("第 " + RunClient.connectionTimes + " 断线重连");
                runClient.reConnect();
            } else {
                //超过重连次数，关闭channel
                ctx.fireExceptionCaught(new Throwable("断线重连 " + RunClient.connectionTimes + " 次")).close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 暂时只支持一次接收一个完整的消息
        // 如需要处理组合消息（长消息分多次发），需要另外处理，特别是解码逻辑
        IMProtoMessage<MessageLite> message = (IMProtoMessage<MessageLite>) msg;

        // ctx.fireChannelReadComplete();
        IMHeader header = message.getHeader();
//        this.msg(ctx);
        // 处理请求分发
        switch (header.getServiceId()) {
            case IMBaseDefine.ServiceID.SID_HEART_BEAT_VALUE:
                clientHandlerManager.doHeartBeat(ctx, header.getCommandId(), header, message.getBody());
                break;
            case IMBaseDefine.ServiceID.SID_ACTION_VALUE:
                clientHandlerManager.doAction(ctx, header.getCommandId(), header, message.getBody());
                break;
            default:
                log.warn("暂不支持的服务ID{}", header.getServiceId());
                break;
        }


    }


    private void msg(ChannelHandlerContext ctx) {
        // 发送随机消息
        byte[] msgData = SecurityUtils.getInstance().EncryptMsg(RandomStringUtils.randomAscii(20));
        IMMessage.IMMsgData msg = IMMessage.IMMsgData.newBuilder().setFromUserId(12345)
                .setToSessionId(123456).setMsgId(0).setMsgType(IMBaseDefine.MsgType.MSG_TYPE_SINGLE_TEXT).setMsgData(ByteString.copyFrom(msgData)).setCreateTime(CommonUtils.currentTimeSeconds()).build();
        IMHeader header = new DefaultIMHeader(IMBaseDefine.MessageCmdID.CID_MSG_DATA_VALUE);
        ctx.writeAndFlush(new IMProtoMessage<IMMessage.IMMsgData>(header, msg));
    }
}

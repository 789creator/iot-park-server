package com.szzt.iot.agent.bridge.client.manager;

import com.google.protobuf.MessageLite;
import com.szzt.iot.agent.bridge.client.handler.ClientScriptHandler;
import com.szzt.iot.agent.bridge.client.handler.impl.ClientScriptHandlerImpl;
import com.szzt.iot.agent.bridge.client.handler.impl.HeartBeatHandlerImpl;
import com.szzt.iot.common.netty.IMHeader;
import com.szzt.iot.common.netty.proto.IMBaseDefine;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端接收到消息处理分发
 *
 * @author zhouhongjin
 */
@Slf4j
public class ClientHandlerManager {

    private HeartBeatHandlerImpl heartBeatHandler = new HeartBeatHandlerImpl();
    private ClientScriptHandler clientScriptHandler = new ClientScriptHandlerImpl();

    /**
     * 客户端收到服务端的心跳处理
     *
     * @param ctx
     * @param commandId
     * @param header
     * @param body
     */
    public void doHeartBeat(ChannelHandlerContext ctx, short commandId, IMHeader header, MessageLite body) {
        heartBeatHandler.doHeartBeat(header,body,ctx);
    }

    /**
     * 处理Sikulix脚本
     *
     * @param ctx
     * @param commandId
     * @param header
     * @param body
     */
    public void doAction(ChannelHandlerContext ctx, short commandId, IMHeader header, MessageLite body) {
        log.debug("doMessage commandId: {}", commandId);
        log.debug("doMessage body: {}", body.toString());
        switch (commandId) {
            case IMBaseDefine.ActionCmdID.CID_ACTION_EXE_SIKULIX_SCRIPT_VALUE:
                clientScriptHandler.doSikulixScript(header, body, ctx);
                break;
            case IMBaseDefine.ActionCmdID.CID_ACTION_EXE_SELENIUM_SCRIPT_VALUE:
                clientScriptHandler.doSeleniumScript(header, body, ctx);
                break;
            case IMBaseDefine.ActionCmdID.CID_ACTION_SYNC_SCRIPT_VALUE:
                clientScriptHandler.doSyncScript(header, body, ctx);
                break;
            default:
                log.warn("Unsupport command id {}", commandId);
                break;
        }
    }


    /**
     * @param ctx
     * @since 1.0
     */
    public void remove(ChannelHandlerContext ctx) {
        //todo
    }

    /**
     * 掉线时的处理
     *
     * @param ctx
     * @since 1.0
     */
    public void offline(ChannelHandlerContext ctx) {
        //todo
    }


    /**
     * 连接时的处理
     *
     * @param ctx
     * @since 1.0
     */
    public void online(ChannelHandlerContext ctx) {
        //todo
    }


}

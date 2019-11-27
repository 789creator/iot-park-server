package com.szzt.iot.agent.bridge.client.handler;

import com.google.protobuf.MessageLite;
import com.szzt.iot.common.netty.IMHeader;
import io.netty.channel.ChannelHandlerContext;

public interface ClientScriptHandler {

    /**
     * 执行sikulix脚本
     *
     * @param header
     * @param body
     * @param ctx
     */
    void doSikulixScript(IMHeader header, MessageLite body, ChannelHandlerContext ctx);

    /**
     * 执行selenium脚本
     *
     * @param header
     * @param body
     * @param ctx
     */
    void doSeleniumScript(IMHeader header, MessageLite body, ChannelHandlerContext ctx);

    /**
     * 执行脚本同步
     *
     * @param header
     * @param body
     * @param ctx
     */
    void doSyncScript(IMHeader header, MessageLite body, ChannelHandlerContext ctx);
}

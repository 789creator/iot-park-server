package com.szzt.iot.admin.modules.netty.server.handler;

import com.google.protobuf.MessageLite;
import com.szzt.iot.common.netty.IMHeader;
import io.netty.channel.ChannelHandlerContext;

public interface IMTestHandler {

    public void test(IMHeader header, MessageLite msg, ChannelHandlerContext ctx) throws Exception;
}

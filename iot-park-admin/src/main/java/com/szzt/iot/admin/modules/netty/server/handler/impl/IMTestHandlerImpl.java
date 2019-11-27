package com.szzt.iot.admin.modules.netty.server.handler.impl;

import com.google.protobuf.MessageLite;
import com.szzt.iot.common.netty.IMHeader;
import com.szzt.iot.admin.modules.netty.server.handler.IMTestHandler;
import io.netty.channel.ChannelHandlerContext;

public class IMTestHandlerImpl implements IMTestHandler {
    @Override
    public void test(IMHeader header, MessageLite msg, ChannelHandlerContext ctx) throws Exception {

    }
}

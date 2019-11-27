package com.szzt.iot.admin.modules.netty.server;

import com.szzt.iot.common.netty.PacketDecoder;
import com.szzt.iot.common.netty.PacketEncoder;
import com.szzt.iot.admin.modules.netty.server.handler.MessageServerHandler;
import com.szzt.iot.admin.modules.netty.server.manager.ServerHandlerManager;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * 服务端初始化handler
 *
 * @author zhouhongjin
 */
public class ServerInitHandler extends ChannelInitializer<SocketChannel> {

    private ServerHandlerManager handlerManager =new ServerHandlerManager();

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //管道注册handler
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("logger", new LoggingHandler());
        pipeline.addLast("framer", new LengthFieldBasedFrameDecoder(400 * 1024, 0, 4, -4, 0));
        pipeline.addLast("decoder", new PacketDecoder());
        pipeline.addLast("encoder", new PacketEncoder());
        pipeline.addLast("handler", new MessageServerHandler(handlerManager));



    }
}

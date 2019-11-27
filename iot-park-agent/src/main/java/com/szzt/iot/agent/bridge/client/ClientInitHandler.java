package com.szzt.iot.agent.bridge.client;

import com.szzt.iot.agent.bridge.client.manager.ClientHandlerManager;
import com.szzt.iot.agent.bridge.client.manager.ClientMessageHandler;
import com.szzt.iot.common.netty.PacketDecoder;
import com.szzt.iot.common.netty.PacketEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 客户端初始化 handler
 *
 * @author zhouhongjin
 */
public class ClientInitHandler extends ChannelInitializer<SocketChannel> {
    private ClientHandlerManager clientHandlerManager = new ClientHandlerManager();

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //2分钟没有收到消息，触发重连
        pipeline.addLast("idleState", new IdleStateHandler(2 * 60, 0, 0, TimeUnit.SECONDS));
        pipeline.addLast("framer", new LengthFieldBasedFrameDecoder(400 * 1024, 0, 4, -4, 0));
        pipeline.addLast("decoder", new PacketDecoder());
        pipeline.addLast("encoder", new PacketEncoder());
        pipeline.addLast(new LoggingHandler(LogLevel.WARN));
        pipeline.addLast("handler", new ClientMessageHandler(clientHandlerManager));
    }
}

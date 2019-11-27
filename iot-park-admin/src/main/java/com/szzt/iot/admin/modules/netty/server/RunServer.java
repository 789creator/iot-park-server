package com.szzt.iot.admin.modules.netty.server;

import com.szzt.iot.admin.modules.netty.server.threadworkers.Server2WebClient;
import com.szzt.iot.common.netty.analysis.ProtobufParseInit;
import com.szzt.iot.admin.modules.netty.server.manager.ClientConnectionInfo;
import com.szzt.iot.admin.modules.netty.server.manager.ClientConnectionMap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * netty启动入口
 *
 * @author zhouhongjin
 */
@Slf4j
public class RunServer implements Runnable {
    /**
     * 端口号
     */
    private int port;

    private ChannelFuture channelFuture;

    public RunServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        this.initProtobuf();
        this.heartBeatCheckStart();
        this.initSetting();
        // 启动 Server2WebClient
        try {
            new Server2WebClient(1).start();
            log.info("Server2WebClient启动完成");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Server2WebClient启动失败");
        }
        //nettyStart 需要放在最后面执行
        this.nettyStart();

    }

    private void initSetting() {
        //TODO 写到配置里面 io.netty.threadLocalDirectBufferSize 默认是0 ，不设置会报错
        System.setProperty("io.netty.threadLocalDirectBufferSize", String.valueOf(8 * 1024));
    }

    /**
     * 心跳检测启动
     */
    private void heartBeatCheckStart() {

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Iterator<Map.Entry<String, ClientConnectionInfo>> iterator = ClientConnectionMap.connectionMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, ClientConnectionInfo> entry = iterator.next();
                    ClientConnectionInfo clientConnectionInfo = entry.getValue();
                    //心跳检测计数
                    int heartBeatCheckNum = clientConnectionInfo.getHeartBeatCheckNum();
                    // 多次发送心跳，没有回复，则移除连接
                    if (heartBeatCheckNum >= 2) {
                        //关闭连接
                        iterator.remove();
                        ClientConnectionMap.removeClient(clientConnectionInfo);
                        continue;
                    }
                    clientConnectionInfo.setHeartBeatCheckNum(heartBeatCheckNum + 1);
                    HeartBeatCheck.sendHeartBeatMsg(clientConnectionInfo.getId(), clientConnectionInfo.getChannelHandlerContext());
                }
            }
            //心跳间隔时长 60 秒
        }, 60, 60, TimeUnit.SECONDS);

    }

    public void nettyStart() {
        //Group：群组，Loop：循环，Event：事件，这几个东西联在一起，相比大家也大概明白它的用途了。
        //Netty内部都是通过线程在处理各种数据，EventLoopGroup就是用来管理调度他们的，注册Channel，管理他们的生命周期。
        //NioEventLoopGroup是一个处理I/O操作的多线程事件循环
        //bossGroup作为boss,接收传入连接
        //因为bossGroup仅接收客户端连接，不做复杂的逻辑处理，为了尽可能减少资源的占用，取值越小越好
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //workerGroup作为worker，处理boss接收的连接的流量和将接收的连接注册进入这个worker
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap负责建立服务端
            //你可以直接使用Channel去建立服务端，但是大多数情况下你无需做这种乏味的事情
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    //指定使用NioServerSocketChannel产生一个Channel用来接收连接
                    .channel(NioServerSocketChannel.class)
                    //ChannelInitializer用于配置一个新的Channel
                    //用于向你的Channel当中添加ChannelInboundHandler的实现
                    .childHandler(new ServerInitHandler())
                    //对Channel进行一些配置
                    //注意以下是socket的标准参数
                    //BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
                    //Option是为了NioServerSocketChannel设置的，用来接收传入连接的
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
                    //childOption是用来给父级ServerChannel之下的Channels设置参数的
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // Bind and start to accept incoming connections.
            log.info("netty 服务器端口：" + port);
            channelFuture = b.bind(port).sync();
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            //sync()会同步等待连接操作结果，用户线程将在此wait()，直到连接操作完成之后，线程被notify(),用户代码继续执行
            //closeFuture()当Channel关闭时返回一个ChannelFuture,用于链路检测
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //资源优雅释放
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    /**
     * 初始化Protobuf参数
     *
     * @since 1.0
     */
    private void initProtobuf() {
        ProtobufParseInit.init();
    }

    public static void main(String[] args) {
        RunServer runServer = new RunServer(8806);
        runServer.run();
    }
}

package com.szzt.iot.agent.bridge.client;

import com.google.protobuf.ByteString;
import com.szzt.iot.common.netty.DefaultIMHeader;
import com.szzt.iot.common.netty.IMHeader;
import com.szzt.iot.common.netty.IMProtoMessage;
import com.szzt.iot.common.netty.analysis.ProtobufParseInit;
import com.szzt.iot.common.netty.proto.IMBaseDefine;
import com.szzt.iot.common.netty.proto.IMMessage;
import com.szzt.iot.common.utils.CommonUtils;
import com.szzt.iot.common.utils.SecurityUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Data;

/**
 * netty客户端启动入口
 *
 * @author zhouhongjin
 */
@Data
public class RunClient {

    public static RunClient runClient;
    public static Bootstrap bootstrap;
    public static Channel clientChannel;
    /**
     * netty 服务端ip
     */
    private String ip;
    /**
     * netty 服务端端口
     */
    private int port;
    /**
     * 重连次数
     */
    public static int connectionTimes = 0;

//    public RunClient(String ip, int port) {
//        this.ip = ip;
//        this.port = port;
//    }

    private RunClient() {

    }

    public synchronized static RunClient getInstance() {
        if (runClient == null) {
            runClient = new RunClient();
        }
        return runClient;
    }


    public void start() {

        this.initProtobuf();
        this.initSetting();
        //nettyStart 需要放在最后面执行
        this.nettyClientStart();

    }

    private void initProtobuf() {
        ProtobufParseInit.init();
    }

    private void nettyClientStart() {
        //设置一个多线程循环器
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //启动附注类
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        //指定所使用的NIO传输channel
        bootstrap.channel(NioSocketChannel.class);
        //指定客户端初始化处理
        bootstrap.handler(new ClientInitHandler());
        try {
            //连接服务
            clientChannel = bootstrap.connect(ip, port).sync().channel();
//            while (true) {
//                //向服务端发送内容，主要也是为了保持main方法不退出
//                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//                String content = reader.readLine();
//                if (StringUtils.isNotEmpty(content)) {
//                    if (StringUtils.equalsIgnoreCase(content, "q")) {
//                        System.exit(1);
//                    }
//                    this.msg(clientChannel, content);
//                }
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
//        } catch (IOException e) {
//            e.printStackTrace();
        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            workerGroup.shutdownGracefully();
//        }
    }

    private void initSetting() {
        //TODO 写到配置里面 io.netty.threadLocalDirectBufferSize 默认是0 ，不设置会报错
        System.setProperty("io.netty.threadLocalDirectBufferSize", String.valueOf(8 * 1024));
    }

    /**
     * 重连
     */
    public synchronized void reConnect() {
        try {
            connectionTimes++;
            //关闭上次的channel
            clientChannel.close();
            clientChannel = bootstrap.connect(ip, port).sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private void msg(Channel channel, String content) {
        // 发送随机消息
        byte[] msgData = SecurityUtils.getInstance().EncryptMsg(content);
        IMMessage.IMMsgData msg = IMMessage.IMMsgData.newBuilder().setFromUserId(Long.valueOf("12345"))
                .setToSessionId(Long.valueOf("123456") + 2).setMsgId(0).setMsgType(IMBaseDefine.MsgType.MSG_TYPE_SINGLE_TEXT).setMsgData(ByteString.copyFrom(msgData)).setCreateTime(CommonUtils.currentTimeSeconds()).build();
        IMHeader header = new DefaultIMHeader(IMBaseDefine.MessageCmdID.CID_MSG_DATA_VALUE);
        channel.writeAndFlush(new IMProtoMessage<IMMessage.IMMsgData>(header, msg));
    }

    public static void main(String[] args) {
        System.setProperty("io.netty.threadLocalDirectBufferSize", String.valueOf(8 * 1024));
//        new RunClient("127.0.0.1", 8806).run();
    }
}

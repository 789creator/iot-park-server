package com.szzt.iot.admin.modules.netty.server.config;

import com.szzt.iot.admin.modules.netty.server.RunServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * netty服务端配置类,通过@EnableNettyServer来启用该类
 *
 * @author zhouhongjin
 */
public class NettyServerConfig {

    @Value("${netty.port:8806}")
    private int port;

    /**
     * 启动netty服务端，需放在最后面实例化bean
     * @return
     */
    @Bean
    public RunServer runServer(){
        RunServer runServer = new RunServer(port);
        Thread thread = new Thread(runServer);
        thread.start();
        return runServer;
    }
}

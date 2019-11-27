package com.szzt.iot.admin.modules.netty.server.threadworkers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 服务端通过websocket 发送脚本同步结果信息到前端
 */
public class Server2WebClient implements DataTransfer {

    public static BlockingQueue<Server2WebClientData> up2WebClient =  new LinkedBlockingQueue<Server2WebClientData>();
    private final int poolSize;
    ExecutorService exService;

    public Server2WebClient( int poolSize) {
        super();
        this.poolSize = poolSize;
        exService = Executors.newFixedThreadPool(poolSize, new ThreadFactoryImpl("up2WebClient_", false));
    }


    @Override
    public void run() {
        for (int i = 0; i < poolSize; i++) {
            exService.execute(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        Server2WebClientData channelData = null;
                        try {
                            channelData = up2WebClient.take();//获取队列内容
                            if (channelData == null) {
                                continue;
                            }
                            // 通过websocket反馈给前端界面

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

    }


    @Override
    public void start() throws Exception {
        new Thread(this).start();
    }

}

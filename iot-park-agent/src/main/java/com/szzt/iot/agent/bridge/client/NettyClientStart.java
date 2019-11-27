package com.szzt.iot.agent.bridge.client;//package org.sikuli.bridge.client;
//
//import cn.hutool.core.util.StrUtil;
//import lombok.Data;
//
///**
// * netty客户端启动类
// *
// * @author zhouhongjin
// */
//@Data
//public class NettyClientStart {
//    // TODO 读取配置文件
//    private static final String ip = "127.0.0.1";
//    private static final int port = 8806;
//
//
//    /**
//     * 启动netty客户端，需放在最后面实例化bean
//     *
//     * @return
//     */
//    public static RunClient runClient() {
//        System.out.println("启动netty中.......");
//        String property = System.getProperty("io.netty.threadLocalDirectBufferSize");
//        if (StrUtil.isEmpty(property)) {
//            //设置一个默认值，否则会报错
//            System.setProperty("io.netty.threadLocalDirectBufferSize", String.valueOf(64 * 1024));
//        }
//        RunClient runClient = new RunClient(ip, port);
//        Thread thread = new Thread(runClient);
//        thread.start();
//        return runClient;
//    }
//
//}

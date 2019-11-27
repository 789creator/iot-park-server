package com.szzt.iot.test;


import org.sikuli.script.FindFailed;

/**
 * netty启动类
 */
public class Bootstrap {
    static Object lock = new Object();

    public static void main(String[] args) {

//        String property = System.getProperty("io.netty.threadLocalDirectBufferSize");
//        if (StrUtil.isEmpty(property)) {
//            //设置一个默认值，否则会报错
//            System.setProperty("io.netty.threadLocalDirectBufferSize", String.valueOf(64 * 1024));
//        }
////        RunClient runClient = new RunClient();
//        RunClient runClient = RunClient.getInstance();
//        String ip = PropSettings.setting.get("nettyClient", "ip");
//        String port = PropSettings.setting.get("nettyClient", "port");
//        runClient.setIp(ip);
//        runClient.setPort(Integer.parseInt(port));
//        // 注意：如果不开启线程
//        runClient.start();
//        String startGatewayServer = PropSettings.setting.get("startGatewayServer");
//        if (startGatewayServer.equals("true")) {
            // 启动 api 的GatewayServer
            try {
                org.sikuli.script.Sikulix.main(new String[]{"-p"});
//            args = new String[]{"-p"};
//            RunTime.start(RunTime.Type.API, args);
//            SikulixAPI.main(args);
            } catch (FindFailed findFailed) {
                findFailed.printStackTrace();
            }
        }
//        while (true) {
//            //向服务端发送内容，主要也是为了保持main方法不退出
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            String content = null;
//            try {
//                content = reader.readLine();
//                System.out.println("content:" + content);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (StringUtils.isNotEmpty(content)) {
//                if (StringUtils.equalsIgnoreCase(content, "q")) {
//                    System.exit(1);
//                }
//            }
//        }

//    }
}

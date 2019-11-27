package com.szzt.iot.agent;

import org.sikuli.basics.Settings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

//import com.szzt.iot.common.sikulix.CmdSikulix;
//import org.sikuli.script.FindFailed;
//import org.sikuli.script.Sikulix;

/**
 * robot-agent 启动入口
 *
 * @author
 */
@SpringBootApplication
//@EnableNettyClient
@ComponentScan("com.szzt.iot")
public class AgentApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AgentApplication.class, args);
        // 启动 Sikulix-Api
        initSystemProperty();
//        new CmdSikulix().start();
//        try {
//            CmdSikulix.cmdStartSikulixApi();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try {
//            Sikulix.main(new String[]{"-p"});
//        } catch (FindFailed findFailed) {
//            findFailed.printStackTrace();
//        }

    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AgentApplication.class);
    }

    /**
     * Sikulix 环境变量和系统变量，这里值得注意（可能有些错误就是环境属性的问题）
     */
    private static void initSystemProperty() {
        // false，表示该机器支持图形界面操作
        System.setProperty("java.awt.headless", String.valueOf(false));
        //home路径，以及sikulix路径  详见sikulix的Settings类，此处只针对window系统进行设置
//        ClassPathResource classPathResource = new ClassPathResource("AppData");
//        System.getenv("APPDATA",classPathResource.getAbsolutePath());
    }

    /**
     * 检测是否有相关的依赖包，如果没有就安装
     */
    private static void checkSetup() {
//        String sikulixDataPath = Settings.getSikuliDataPath();
//        File sikulixFile = new File(sikulixDataPath);
//        if (!sikulixFile.exists()) {
//            sikulixFile.mkdirs();
//        }
//        File[] files = sikulixFile.listFiles();
//        boolean hasExtensions = false;
//        boolean hasLib = false;
//        for (File file : files) {
//
//            if (file.getName().equals("Extensions")) {
//                hasExtensions = true;
//            } else if (file.getName().equals("Lib")) {
//                hasLib = true;
//            }
//        }
//        if (!hasExtensions || !hasLib) {
//            System.out.println("不存在：" + sikulixDataPath + "\\Extensions 文件夹或者存在：" + sikulixDataPath + "\\Lib 文件夹");
//            new RunSetup().setup();
//        }
    }
}

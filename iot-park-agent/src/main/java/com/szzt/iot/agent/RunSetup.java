package com.szzt.iot.agent;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import org.sikuli.basics.Settings;

import java.io.File;

/**
 * sikulix 依赖安装
 * @author zhouhongjin
 */
public class RunSetup {

    public void setup() {
//        String sikuliDataPath = Settings.getSikuliDataPath();
//        System.out.println("========开始安装==========");
//        System.out.println("========sikuliDataPath：" + sikuliDataPath + "==========");
//        System.out.println(sikuliDataPath);
//        System.out.println("========拷贝jar包中==========");
//        File target = new File(sikuliDataPath);
//        ClassPathResource classPathResource = new ClassPathResource("Sikulix");
//        File file = classPathResource.getFile();
//        File[] files = file.listFiles();
//        for (File src : files) {
//            FileUtil.copy(src, target, true);
//        }
//        System.out.println("========拷贝jar包结束，可以运行程序了==========");
    }

    public static void main(String[] args) {
        new RunSetup().setup();
    }
}

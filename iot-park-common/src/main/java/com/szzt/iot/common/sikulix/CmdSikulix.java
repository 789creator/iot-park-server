package com.szzt.iot.common.sikulix;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;

import java.io.IOException;
import java.util.List;

public class CmdSikulix extends Thread{

    private static String jarName = "sikulixapi.jar";
    private static String pidName = "sikulixapi";
    public static String jarPath;

    @Override
    public void run() {
        try {
            cmdStartSikulixApi();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注意其他进程不能包含 pidName，否则会被结束进程
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public static synchronized void cmdStartSikulixApi() throws IOException, InterruptedException {
        stopProcess();
        ClassPathResource classPathResource = new ClassPathResource("Sikulix");
        jarPath = classPathResource.getAbsolutePath() + "/api/" + jarName;
        System.out.println("jar 的位置：" + jarPath);

        String exec = RuntimeUtil.execForStr("javaw -jar " + jarPath + " -p");
        System.out.println(exec);
    }

    /**
     * 结束进程
     */
    private static void stopProcess() {
        List<String> lines = RuntimeUtil.execForLines("cmd /c jps | findstr /I " + pidName);
        System.out.println(lines);
        if (lines != null && lines.size() > 0) {
            for (String line : lines) {
                String pid = line.split(" ")[0];
                RuntimeUtil.execForStr("cmd /c taskkill /pid " + pid + " -f");

            }
        }
    }

    public static void main(String[] args) {
        try {
            cmdStartSikulixApi();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

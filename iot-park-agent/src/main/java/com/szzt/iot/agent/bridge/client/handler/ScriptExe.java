package com.szzt.iot.agent.bridge.client.handler;

import cn.hutool.core.util.RuntimeUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ScriptExe {

    public static SikulixScriptExe sikulixScriptExe = new SikulixScriptExe();

    /**
     * 执行纯python代码
     *
     * @param scriptAbsolutePath
     */
    public static void exeScriptPython(String scriptAbsolutePath) {
        try {

            Process exec = Runtime.getRuntime().exec("cmd /c python " + scriptAbsolutePath);
            exec.waitFor();
            if (exec.exitValue() != 0) {
                log.error("执行失败：{}", scriptAbsolutePath);
                System.out.println("执行失败：" + scriptAbsolutePath);

            }
//            InputStream inputStream = exec.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            String s = null;
//            while ((s = reader.readLine()) != null) {
//                System.out.println(s);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行sikulix 的python代码
     *
     * @param scriptAbsolutePath
     */
    public static void exeScriptSikulix(String scriptAbsolutePath) {
//        String cmd = "cmd /c java -jar " + CmdSikulix.jarPath + " -r " + scriptAbsolutePath;
//        System.out.println("==========执行Sikulix cmd======");
//        System.out.println("cmd :" + cmd);
//         TODO 执行失败或者成功
//        String exec = RuntimeUtil.execForStr(cmd);
//        System.out.println(exec);
        String s = sikulixScriptExe.exeScript(scriptAbsolutePath);
        // todo 执行成功发行activemq消息给admin

//        try {
//            ActivMQMessageProducer producer = ActivMQMessageProducer.getInstance();
//            producer.sendMessage("执行成功");
//        } catch (JMSException e) {
//            e.printStackTrace();
//            //TODO 记录日志
//        }

    }

    /**
     * 执行Selenium脚本
     *
     * @param scriptAbsolutePath
     */
    public static void exeScriptSeleniumSide(String scriptAbsolutePath) {

        String cmd = "cmd /c selenium-side-runner " + scriptAbsolutePath;
        System.out.println("==========执行Selenium side cmd======");
        System.out.println("cmd :" + cmd);
        // TODO 执行失败或者成功
        String exec = RuntimeUtil.execForStr(cmd);
        System.out.println(exec);
    }

    /**
     * 执行python 类型的Selenium脚本
     *
     * @param scriptAbsolutePath
     */
    public static void exeScriptSeleniumPython(String scriptAbsolutePath) {
        String cmd = "cmd /c python " + scriptAbsolutePath;
        System.out.println("==========执行Selenium python cmd======");
        System.out.println("cmd :" + cmd);
        // TODO 执行失败或者成功
        String exec = RuntimeUtil.execForStr(cmd);
        System.out.println(exec);
    }
}

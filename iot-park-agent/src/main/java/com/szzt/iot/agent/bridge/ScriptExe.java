package com.szzt.iot.agent.bridge;//package org.sikuli.bridge;
//
//import cn.hutool.core.util.RuntimeUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.sikuli.bridge.client.handler.SikulixScriptExe;
//
//import java.io.IOException;
//
//@Slf4j
//public class ScriptExe {
//
//    public static SikulixScriptExe sikulixScriptExe = new SikulixScriptExe();
//
//    /**
//     * 执行纯python代码
//     *
//     * @param scriptAbsolutePath
//     */
//    public static void exeScriptPython(String scriptAbsolutePath) {
//        try {
//
//            Process exec = Runtime.getRuntime().exec("cmd /c python " + scriptAbsolutePath);
//            exec.waitFor();
//            if (exec.exitValue() != 0) {
//                log.error("执行失败：{}", scriptAbsolutePath);
//                System.out.println("执行失败：" + scriptAbsolutePath);
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 执行sikulix 的python代码
//     *
//     * @param scriptAbsolutePath
//     */
//    public static void exeScriptSikulix(String scriptAbsolutePath) {
//        String s = sikulixScriptExe.exeScript(scriptAbsolutePath);
//    }
//
//    /**
//     * 执行Selenium脚本
//     *
//     * @param scriptAbsolutePath
//     */
//    public static void exeScriptSeleniumSide(String scriptAbsolutePath) {
//
//        String cmd = "cmd /c selenium-side-runner " + scriptAbsolutePath;
//        System.out.println("==========执行Selenium side cmd======");
//        System.out.println("cmd :" + cmd);
//        // TODO 执行失败或者成功
//        String exec = RuntimeUtil.execForStr(cmd);
//        System.out.println(exec);
//    }
//
//    /**
//     * 执行python 类型的Selenium脚本
//     *
//     * @param scriptAbsolutePath
//     */
//    public static void exeScriptSeleniumPython(String scriptAbsolutePath) {
//        String cmd = "cmd /c python " + scriptAbsolutePath;
//        System.out.println("==========执行Selenium python cmd======");
//        System.out.println("cmd :" + cmd);
//        // TODO 执行失败或者成功
//        String exec = RuntimeUtil.execForStr(cmd);
//        System.out.println(exec);
//    }
//
//}

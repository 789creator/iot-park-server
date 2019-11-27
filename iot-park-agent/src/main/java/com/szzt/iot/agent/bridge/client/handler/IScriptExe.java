package com.szzt.iot.agent.bridge.client.handler;

/**
 * 执行脚本接口类
 */
public interface IScriptExe {
    /**
     * 执行脚本
     *
     * @param scriptAbsolutePath
     * @return
     */
    String exeScript(String scriptAbsolutePath);
}

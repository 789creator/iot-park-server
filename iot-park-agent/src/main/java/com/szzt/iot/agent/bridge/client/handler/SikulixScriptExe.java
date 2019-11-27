package com.szzt.iot.agent.bridge.client.handler;

import org.sikuli.script.ImagePath;
import org.sikuli.script.support.IScriptRunner;
import org.sikuli.script.support.RunTime;
import org.sikuli.script.support.Runner;

import java.io.File;

/**
 * sikulix脚本执行器
 */
public class SikulixScriptExe implements IScriptExe {
    @Override
    public String exeScript(String scriptAbsolutePath) {

//        text/python
        File scriptFilePath = new File(scriptAbsolutePath);
        ImagePath.reset(scriptFilePath.getParent());
        IScriptRunner scriptRunner = Runner.getRunner("text/jython");
        if (scriptRunner == null) {
//            return -1;
        }
        IScriptRunner.Options runOptions = new IScriptRunner.Options();
        runOptions.setRunningInIDE();
        int rs = scriptRunner.runScript(scriptAbsolutePath, RunTime.getUserArgs(), runOptions);
        return rs + "";
    }
}

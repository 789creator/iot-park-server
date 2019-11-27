//package com.szzt.iot.agent.sikulix.script;//package com.szzt.iot;
//
//
//import org.sikuli.script.support.IScriptRunner;
//import org.sikuli.script.support.RunTime;
//import org.sikuli.script.support.Runner;
//
//import java.io.File;
//
///**
// * 1.1.4 ，通过IScriptRunner调用执行脚本，与1.1.3 调用不同
// */
//public class ScriptRunnerTestV114 {
//
//    public int runScript(String type, File scriptFile, IScriptRunner.Options options) {
//        IScriptRunner scriptRunner = Runner.getRunner(type);
//        if (scriptRunner == null) {
//            return -1;
//        }
//        int rs = scriptRunner.runScript(scriptFile.getAbsolutePath(), RunTime.getUserArgs(), options);
//        return rs;
//    }
//
//    public static void main(String[] args) {
//        ScriptRunnerTestV114 scriptRunnerTest = new ScriptRunnerTestV114();
//        IScriptRunner.Options options = new IScriptRunner.Options();
//        File scriptFile = new File("E:\\test\\test.sikuli\\test.py");
//        int rs = scriptRunnerTest.runScript("text/jython", scriptFile, options);
//        System.out.println("rs=" + rs);
//
//    }
//}

package com.szzt.iot.agent.sikulix.script;

//import org.sikuli.script.support.IScriptRunner;

import java.io.File;

/**
 * Script 处理接口，不同的脚本handler实现该类的方法
 *
 * @author zhouhongjin
 */
public interface IScriptHandler {
    /**
     * 1.1.3版本，执行脚本
     *
     * @param scriptFile    File containing the scrip
     * @param imagePathFile Directory containing the images (might be null: parent of script)
     * @param scriptArgs    Arguments to be passed directly to the script with --args
     * @param forIDE        when called from Sikuli IDE additional info
     * @return exitcode for the script execution
     */
    int runScriptV113(File scriptFile, File imagePathFile, String[] scriptArgs, String[] forIDE);

    /**
     * 1.1.4版本，执行脚本
     * Executes the Script.
     *
     * @param scriptFile Identifier pointing to the script. This can either by a file path
     *                   or an URI, depending on the runner implementation
     * @param scriptArgs Arguments to be passed directly to the script with --args
     * @param options    Implementation specific options.
     * @return exitcode for the script execution
     */
//    int runScriptV114(String scriptFile, String[] scriptArgs, IScriptRunner.Options options);
}

//package com.szzt.iot.agent.sikulix.ide;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.sikuli.ide.SikuliIDE;
//import org.sikuli.scriptrunner.ScriptingSupport;
//
//import javax.swing.*;
//import java.security.CodeSource;
//
///**
// * SikuliIDE 启动入口
// *
// * @author zhouhongjin
// */
//@AllArgsConstructor
//@Slf4j
//public class SikuliIDEStart extends Thread {
//    String[] args;
//
//    @Override
//    public void run() {
//        //来源于org.sikuli.ide.Sikulix
//        String jarName = "";
//        CodeSource codeSrc = SikuliIDE.class.getProtectionDomain().getCodeSource();
//        if (codeSrc != null && codeSrc.getLocation() != null) {
//            jarName = codeSrc.getLocation().getPath();
//        }
//
//        if (jarName.contains("sikulixsetupIDE")) {
//            JOptionPane.showMessageDialog(null, "Not useable!\nRun setup first!",
//                    "sikulixsetupIDE", JOptionPane.ERROR_MESSAGE);
//            System.exit(0);
//        }
//
//        if (args.length > 0 && args[0].startsWith("-test")) {
//            ScriptingSupport.runscript(args);
//            if (null == args[0]) {
//                System.exit(1);
//            }
//            System.exit(0);
//        }
//        SikuliIDE.run(args);
//    }
//}

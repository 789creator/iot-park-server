//package com.szzt.iot.agent.sikulix.script;
//
//import cn.hutool.core.io.resource.ClassPathResource;
//import com.szzt.iot.common.utils.Result;
//import org.sikuli.script.ImagePath;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.util.HashMap;
//
///**
// * SikuliScript 脚本处理统一方法入口
// *
// * @author zhouhongjin
// */
//@Component
//public class SikuliXScriptHandler {
//    @Autowired
//    private IScriptHandler iScriptHandler;
//    String[] scriptArgs = new String[]{};
//
//    /**
//     * 1.1.3版本，执行脚本
//     *
//     * @param scriptFile 脚本文件 ，例如E:\test\test.sikuli\test.py
//     * @param scriptArgs 一些参数，具体查看silulix1.1.3
//     * @param imagePath  脚本中用的的图片路径文件夹，例如E:\test\test.sikuli
//     * @return
//     */
//    public Result runScriptV113(File scriptFile, String imagePath, String[] scriptArgs) {
//        ImagePath.add(imagePath);
//        File imagePathFile = new File(imagePath);
//        int rs = iScriptHandler.runScriptV113(scriptFile, imagePathFile, scriptArgs, null);
//        HashMap<String, Object> rsMap = new HashMap<>();
//        rsMap.put("exitcode", rs);
//        return new Result().ok(rsMap);
//    }
//
//    public int runScriptV113(File scriptFile, String imagePath) {
//        ImagePath.add(imagePath);
//        File imagePathFile = new File(imagePath);
//        return iScriptHandler.runScriptV113(scriptFile, imagePathFile, scriptArgs, null);
//    }
//
//    public int runScriptV113(File scriptFile, String[] scriptArgs) {
//        ImagePath.add(scriptFile.getParent());
//        int rs = iScriptHandler.runScriptV113(scriptFile, scriptFile.getParentFile(), scriptArgs, null);
//        return rs;
//    }
//
//    /**
//     * 1.1.3版本，执行脚本test
//     *
//     * @param args
//     * @return
//     */
//    public Result testV113(String[] args) {
//        String scriptRelativePath = "Sikulix\\script\\test\\test.sikuli\\test.py";
//        ClassPathResource classPathResource = new ClassPathResource(scriptRelativePath);
//        File scriptFile = classPathResource.getFile();
////        File scriptFile = new File("E:\\test\\test.sikuli\\test.py");
//        String imagePath = scriptFile.getParent();
//        return runScriptV113(scriptFile, imagePath, args);
//    }
//
//
//
//}

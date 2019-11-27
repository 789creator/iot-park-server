package com.szzt.iot.agent.sikulix.api;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Sikulix;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * sikulixapi 启动
 * @author zhouhongjin
 */
public class SikulixApiStart extends Thread{


    @Override
    public void run() {
        try {
            Sikulix.main(new String[]{"-p"});
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }
}

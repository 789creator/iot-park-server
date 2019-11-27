package com.szzt.iot.admin.modules.job.task;

import com.szzt.iot.admin.modules.message.email.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 定时拉取未读邮件
 */
//@Component("mailTask")
public class MailTask implements ITask {

    @Autowired
    private EmailUtils emailUtils;


    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public void run(String params) {
        logger.info("----接收未读邮件开始----");
        try {
            emailUtils.receiveEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

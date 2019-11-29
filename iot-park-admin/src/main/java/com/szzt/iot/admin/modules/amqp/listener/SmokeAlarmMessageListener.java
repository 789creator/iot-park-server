package com.szzt.iot.admin.modules.amqp.listener;

import com.szzt.iot.admin.modules.device.service.DeviceSmokeAlarmService;
import com.szzt.iot.common.utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * 烟雾报警amqp消息监听
 * @author zhouhongjin
 */
public class SmokeAlarmMessageListener implements MessageListener {

    private final static Logger logger = LoggerFactory.getLogger(SmokeAlarmMessageListener.class);

    @Override
    public void onMessage(Message message) {
        try {
            byte[] body = message.getBody(byte[].class);
            String content = new String(body);
            String topic = message.getStringProperty("topic");
            String messageId = message.getStringProperty("messageId");
            logger.info("receive message"
                    + ", topic = " + topic
                    + ", messageId = " + messageId
                    + ", content = " + content);
            //如果创建Session选择的是Session.CLIENT_ACKNOWLEDGE，这里需要手动ACK。
            //message.acknowledge();
            //如果要对收到的消息做耗时的处理，请异步处理，确保这里不要有耗时逻辑。
            //保存到数据库
            DeviceSmokeAlarmService deviceSmokeAlarmService= (DeviceSmokeAlarmService) SpringContextUtils.getBean("deviceSmokeAlarmServiceImpl");
            deviceSmokeAlarmService.saveDB(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

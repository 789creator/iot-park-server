package com.szzt.iot.admin.modules.activemq.producer.impl;

import cn.hutool.json.JSONUtil;
import com.szzt.iot.admin.modules.activemq.producer.IMessageProducerService;
import com.szzt.iot.common.rabbitmq.RabbitmqMsg;
import com.szzt.iot.common.rabbitmq.JudgeReplyResultMsgBody;
import com.szzt.iot.common.rabbitmq.MsgHeader;
import com.szzt.iot.common.rabbitmq.MsgHeaderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.annotation.Resource;
import javax.jms.Topic;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 抽取评委 ，评委回复结果，消息队列生产者
 *
 * @author zhouhongjin
 */
@Slf4j
//@Service("judgeReplyResultProducerServiceImpl")
public class JudgeReplyResultProducerServiceImpl implements IMessageProducerService {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "judgeReplyResultTopic")
    private Topic judgeReplyResultTopic;

    @Override
    public void sendMessage(Object msg) {
        //创建消息头
        MsgHeader msgHeader = new MsgHeader();
        try {
            //获取本机ip地址
            msgHeader.setFromIp(InetAddress.getLocalHost().getHostAddress());
            log.debug("本机的ip：{}", InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        msgHeader.setServiceId(MsgHeaderEnum.ServiceIdEnum.JUDGE_SERVICE.getCode());
        msgHeader.setCmdId(MsgHeaderEnum.JudgeMsgCmdIdEnum.TO_CLIENT_JUDGE_REPLY.getCode());
        msgHeader.setSendTime(System.currentTimeMillis());


        //创建消息体

        RabbitmqMsg<JudgeReplyResultMsgBody> rabbitmqMsg = new RabbitmqMsg<>();
        rabbitmqMsg.setMsgHeader(msgHeader);
        rabbitmqMsg.setMsgBody((JudgeReplyResultMsgBody) msg);

        this.jmsMessagingTemplate.convertAndSend(this.judgeReplyResultTopic, JSONUtil.toJsonStr(rabbitmqMsg));

    }
}

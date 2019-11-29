package com.szzt.iot.admin.modules.amqp;

import com.szzt.iot.admin.modules.device.service.DeviceSmokeAlarmService;
import com.szzt.iot.common.utils.SpringContextUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.qpid.jms.JmsConnection;
import org.apache.qpid.jms.JmsConnectionListener;
import org.apache.qpid.jms.message.JmsInboundMessageDispatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.net.URI;
import java.util.Hashtable;

/**
 * @author zhouhongjin
 */
public abstract class BaseAmqpConsumer {

    Connection connection;
    MessageConsumer consumer;

    /**
     * password签名计算方法，请参见上一篇文档：AMQP客户端接入说明。
     */
    protected String doSign(String toSignString, String secret, String signMethod) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), signMethod);
        Mac mac = Mac.getInstance(signMethod);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(toSignString.getBytes());
        return Base64.encodeBase64String(rawHmac);
    }
}

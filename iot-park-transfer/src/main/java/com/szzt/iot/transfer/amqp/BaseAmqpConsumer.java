package com.szzt.iot.transfer.amqp;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.jms.Connection;
import javax.jms.MessageConsumer;

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

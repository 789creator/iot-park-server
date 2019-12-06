package com.szzt.iot.transfer.amqp;

import com.szzt.iot.transfer.amqp.listener.SmokeAlarmConnectionListener;
import com.szzt.iot.transfer.amqp.listener.SmokeAlarmMessageListener;
import com.szzt.iot.transfer.config.SysParamConfig;
import org.apache.qpid.jms.JmsConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.net.InetAddress;
import java.util.Hashtable;

/**
 * @author zhouhongjin
 */
public class SmokeAlarmAmqpConsumer extends BaseAmqpConsumer {

    private final static Logger logger = LoggerFactory.getLogger(SmokeAlarmAmqpConsumer.class);

    public void startConsumer() throws Exception {
        //参数说明，请参见上一篇文档：AMQP客户端接入说明。
        String accessKey = SysParamConfig.getAccessKey();
        String accessSecret = SysParamConfig.getAccessSecret();
        String consumerGroupId = SysParamConfig.getSmokeAlarmConsumerGroupId();
        long timeStamp = System.currentTimeMillis();
        //签名方法：支持hmacmd5，hmacsha1和hmacsha256
        String signMethod = "hmacsha1";
        //控制台服务端订阅中消费组状态页客户端ID一栏将显示clientId参数。
        //建议使用机器UUID、MAC地址、IP等唯一标识等作为clientId。便于您区分识别不同的客户端。
        String clientId = "transfer-"+InetAddress.getLocalHost().getHostAddress();

        //UserName组装方法，请参见上一篇文档：AMQP客户端接入说明。
        String userName = clientId + "|authMode=aksign"
                + ",signMethod=" + signMethod
                + ",timestamp=" + timeStamp
                + ",authId=" + accessKey
                + ",consumerGroupId=" + consumerGroupId
                + "|";
        //password组装方法，请参见上一篇文档：AMQP客户端接入说明。
        String signContent = "authId=" + accessKey + "&timestamp=" + timeStamp;
        String password = this.doSign(signContent, accessSecret, signMethod);
        //按照qpid-jms的规范，组装连接URL。
//        String connectionUrl = "failover:(amqps://${uid}.iot-amqp.${regionId}.aliyuncs.com?amqp.idleTimeout=80000)"
//                + "?failover.reconnectDelay=30";
        String uid = SysParamConfig.getUid();
        String regionId = SysParamConfig.getRegionId();
        String connectionUrl = "failover:(amqps://"+uid+".iot-amqp."+regionId+".aliyuncs.com?amqp.idleTimeout=80000)"
                + "?failover.reconnectDelay=30";

        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("connectionfactory.SBCF", connectionUrl);
        hashtable.put("queue.QUEUE", "default");
        hashtable.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.qpid.jms.jndi.JmsInitialContextFactory");
        Context context = new InitialContext(hashtable);
        ConnectionFactory cf = (ConnectionFactory) context.lookup("SBCF");
        Destination queue = (Destination) context.lookup("QUEUE");
        // Create Connection
        connection = cf.createConnection(userName, password);
        ((JmsConnection) connection).addConnectionListener(new SmokeAlarmConnectionListener());
        // Create Session
        // Session.CLIENT_ACKNOWLEDGE: 收到消息后，需要手动调用message.acknowledge()
        // Session.AUTO_ACKNOWLEDGE: SDK自动ACK（推荐）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        // Create Receiver Link
        consumer = session.createConsumer(queue);
        consumer.setMessageListener(new SmokeAlarmMessageListener());
    }

}

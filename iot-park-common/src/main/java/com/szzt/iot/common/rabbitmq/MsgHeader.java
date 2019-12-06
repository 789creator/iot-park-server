package com.szzt.iot.common.rabbitmq;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * activemq 消息头
 *
 * @author zhouhongjin
 */
@Data
public class MsgHeader implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 消息服务id
     */
    private int serviceId;
    /**
     * 消息指令id
     */
    private int cmdId;
    /**
     * 消息产生服务器ip
     */
    private String fromIp;
    /**
     * 消息接收服务器ip
     */
    private String toIp;
    /**
     * 消息发送时间
     */
    private String sendTime;

//    public int getServiceId() {
//        return serviceId;
//    }
//
//    public void setServiceId(int serviceId) {
//        this.serviceId = serviceId;
//    }
//
//    public int getCmdId() {
//        return cmdId;
//    }
//
//    public void setCmdId(int cmdId) {
//        this.cmdId = cmdId;
//    }
//
//    public String getFromIp() {
//        return fromIp;
//    }
//
//    public void setFromIp(String fromIp) {
//        this.fromIp = fromIp;
//    }
//
//    public String getToIp() {
//        return toIp;
//    }
//
//    public void setToIp(String toIp) {
//        this.toIp = toIp;
//    }
//
//    public long getSendTime() {
//        return sendTime;
//    }
//
//    public void setSendTime(long sendTime) {
//        this.sendTime = sendTime;
//    }
}

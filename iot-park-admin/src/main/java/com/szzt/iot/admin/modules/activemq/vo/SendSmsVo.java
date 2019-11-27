package com.szzt.iot.admin.modules.activemq.vo;

import lombok.Data;

import java.util.Date;

/**
 * python代码通过activemq 发送过的消息vo
 *
 * @author zhouhongjin
 */
@Data
public class SendSmsVo {
    /**
     * 消息生成的时间
     */
    private Date currentTime;
    /**
     * 消息来源主机ip
     */
    private String fromIp;
    /**
     * 接收人电话号码
     */
    private String[] to;
    /**
     * 发送内容
     */
    private String content;
    /**
     * 项目编号
     */
    private String projectNo;
    /**
     * 项目名称
     */
    private String projectName;
}

package com.szzt.iot.common.rabbitmq.smokealarm;

import lombok.Data;

import java.io.Serializable;

/**
 * 烟雾报警 msg body
 *
 * @author zhouhongjin
 */
@Data
public class SmokeAlarmMsgBody implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 项目编号
     */
    private String projectNO;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 评委电话号码
     */
    private String judgePhone;
    /**
     * 是否参加评标：1是，0否
     */
    private Integer isBidEvaluation;

}

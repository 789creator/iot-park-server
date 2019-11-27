package com.szzt.iot.common.activemq;

import lombok.Data;

import java.io.Serializable;

/**
 * 抽取评委，评委回复结果消息体
 *
 * @author zhouhongjin
 */
@Data
public class JudgeReplyResultMsgBody implements Serializable{
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

package com.szzt.iot.common.rabbitmq.sikulix;

import lombok.Data;

import java.io.Serializable;

/**
 * sikulix 相关操作的消息体
 *
 * @author zhouhongjin
 */
@Data
public class SikulixScriptMsgBody implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 脚本id
     */
    private Long scriptId;
    /**
     * 脚本名称
     */
    private String scriptName;
    /**
     * 脚本相对路径
     */
    private String scriptPath;
    /**
     * 客户端ip
     */
    private String clientIp;
    /**
     * 脚本执行完成之后，是否返回执行结果
     */
    private boolean isFeedback;

    public Long getScriptId() {
        return scriptId;
    }

    public void setScriptId(Long scriptId) {
        this.scriptId = scriptId;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getScriptPath() {
        return scriptPath;
    }

    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public boolean isFeedback() {
        return isFeedback;
    }

    public void setFeedback(boolean feedback) {
        isFeedback = feedback;
    }
}

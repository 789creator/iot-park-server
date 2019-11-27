package com.szzt.iot.common.activemq.sikulix;

import lombok.Data;

import java.io.Serializable;


/**
 * sikulix 相关操作的消息体
 *
 * @author zhouhongjin
 */
@Data
public class FtpFileMsgBody implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    private Long workId;
    /**
     * 本机ip
     */
    private String ftpIp;

    /**
     * 文件路径,相对路径
     */
    private String filePath;
    /**
     * 文件名
     */
    private String fileName;

    /**
     * 本机ip
     */
    private String clientIp;

    /**
     * 脚本地址
     */
    private String scriptPath;

    /**
     * 下一脚本地址
     */
    private String nextScriptPath;

    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 脚本执行完成之后，是否返回执行结果
     */
    private boolean isFeedback;
}

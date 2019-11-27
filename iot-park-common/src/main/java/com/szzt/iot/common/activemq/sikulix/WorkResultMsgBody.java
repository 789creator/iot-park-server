package com.szzt.iot.common.activemq.sikulix;

import lombok.Data;

import java.io.Serializable;

/**
 *  返回给服务端执行结果消息体
 *
 * @author zhouhongjin
 */
@Data
public class WorkResultMsgBody implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  任务id
     */
    private Long workId;

    /**
     *  结果路径
     */
    private String workResultPath;

    /**
     * 结果文件名
     */
    private String workResultFileName;

    /**
     * 结果 0 ：未执行   1：已执行完成  2 ：执行失败
     */
    private int result;

    /**
     * 结果描述
     */
    private String resultDesc;

    /**
     * 执行机器
     */
    private String serverIp;

    /**
     * 执行成功工单数
     */
    private Integer workOrderNumSuccess;

    /**
     * 执行失败工单数
     */
    private Integer workOrderNumFailed;

}

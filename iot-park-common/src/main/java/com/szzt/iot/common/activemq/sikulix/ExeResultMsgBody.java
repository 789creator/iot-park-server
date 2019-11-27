package com.szzt.iot.common.activemq.sikulix;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回给服务端执行详细结果消息体
 *
 * @author zhouhongjin
 */
@Data
public class ExeResultMsgBody implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    private Long taskId;

    /**
     * 工单编号
     */
    @Excel(name = "工单编号")
    private String workOrder;

    /**
     * 工单记录
     */
    @Excel(name = "工单记录")
    private int amount;

    /**
     * 处理结果
     */
    @Excel(name = "处理结果")
    private String result;

    /**
     * 结果原因
     */
    @Excel(name = "原因")
    private String resultReason;

    /**
     * 执行机器ip
     */
    private String serverIp;

    /**
     * 结果路径
     */
    private String  resultPath;

}

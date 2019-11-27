package com.szzt.iot.admin.modules.agent.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 系统监控表（会定时删除这个表的数据）
 *
 * @author szzt szzt@szzt.com
 * @since 1.0.0 2019-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("agent_system_monitor")
public class AgentSystemMonitorEntity {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 系统时间
     */
    private String sysTime;
    /**
     * 系统名称
     */
    private String osName;
    /**
     *
     */
    private String osArch;
    /**
     *
     */
    private String osVersion;
    /**
     *
     */
    private String userLanguage;
    /**
     *
     */
    private String userDir;
    /**
     *
     */
    private Long totalPhysical;
    /**
     *
     */
    private Long freePhysical;
    /**
     *
     */
    private BigDecimal memoryRate;
    /**
     *
     */
    private Integer processors;
    /**
     *
     */
    private String jvmName;
    /**
     *
     */
    private String javaVersion;
    /**
     *
     */
    private String javaHome;
    /**
     *
     */
    private Long javaTotalMemory;
    /**
     *
     */
    private Long javaFreeMemory;
    /**
     *
     */
    private Long javaMaxMemory;
    /**
     *
     */
    private String userName;
    /**
     *
     */
    private BigDecimal systemCpuLoad;
    /**
     *
     */
    private String userTimezone;
    /**
     *
     */
    private String serverIp;
    /**
     * 记录创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    // 当前程序部署在系统中的路径
    private String currentDisk;
    // 当前程序部署的磁盘总大小
    private Long currentDiskTotalSpace;
    // 当前程序部署的磁盘空闲大小
    private Long currentDiskFreeSpace;
    // 当前程序部署的磁盘使用率
    private BigDecimal currentDiskUsageRate;
}
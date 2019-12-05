package com.szzt.iot.common.rabbitmq.system;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 系统监控数据
 */
@Data
public class SystemMonitorBody {
    /**
     * 系统当前时间
     */
    private String sysTime;
    /**
     * 系统当名称
     */
    private String osName;
    /**
     *
     */
    private String osArch;
    private String osVersion;
    private String userLanguage;
    private String userDir;
    private Long totalPhysical;
    private Long freePhysical;
    private BigDecimal memoryRate;
    private Integer processors;
    private String jvmName;
    private String javaVersion;
    private String javaHome;
    private Long javaTotalMemory;
    private Long javaFreeMemory;
    private Long javaMaxMemory;
    private String userName;
    private BigDecimal systemCpuLoad;
    private String userTimezone;
    private String serverIp;
    // 当前程序部署在系统中的路径
    private String currentDisk;
    // 当前程序部署的磁盘总大小
    private Long currentDiskTotalSpace;
    // 当前程序部署的磁盘空闲大小
    private Long currentDiskFreeSpace;
    // 当前程序部署的磁盘使用率
    private BigDecimal currentDiskUsageRate;
}

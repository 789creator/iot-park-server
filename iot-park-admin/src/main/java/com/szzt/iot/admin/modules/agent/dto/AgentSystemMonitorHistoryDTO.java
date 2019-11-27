package com.szzt.iot.admin.modules.agent.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 系统监控表（会定时删除这个表的数据）
 *
 * @author szzt szzt@szzt.com
 * @since 1.0.0 2019-07-17
 */
@Data
@ApiModel(value = "系统监控历史记录（会定时删除这个表的数据）")
public class AgentSystemMonitorHistoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "系统时间")
	private String sysTime;

	@ApiModelProperty(value = "系统名称")
	private String osName;

	@ApiModelProperty(value = "")
	private String osArch;

	@ApiModelProperty(value = "")
	private String osVersion;

	@ApiModelProperty(value = "")
	private String userLanguage;

	@ApiModelProperty(value = "")
	private String userDir;

	@ApiModelProperty(value = "")
	private Long totalPhysical;

	@ApiModelProperty(value = "")
	private Long freePhysical;

	@ApiModelProperty(value = "")
	private BigDecimal memoryRate;

	@ApiModelProperty(value = "")
	private Integer processors;

	@ApiModelProperty(value = "")
	private String jvmName;

	@ApiModelProperty(value = "")
	private String javaVersion;

	@ApiModelProperty(value = "")
	private String javaHome;

	@ApiModelProperty(value = "")
	private Long javaTotalMemory;

	@ApiModelProperty(value = "")
	private Long javaFreeMemory;

	@ApiModelProperty(value = "")
	private Long javaMaxMemory;

	@ApiModelProperty(value = "")
	private String userName;

	@ApiModelProperty(value = "")
	private BigDecimal systemCpuLoad;

	@ApiModelProperty(value = "")
	private String userTimezone;

	@ApiModelProperty(value = "")
	private String serverIp;


}
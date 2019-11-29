package com.szzt.iot.transfer.db.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 烟雾报警数据
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-11-28
 */
@Data
@ApiModel(value = "烟雾报警数据")
public class DeviceSmokeAlarmDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "备注名称")
	private String deviceName;

	@ApiModelProperty(value = "节点类型")
	private Integer deviceType;

	@ApiModelProperty(value = "状态/启用状态")
	private Integer deviceStatus;

	@ApiModelProperty(value = "电池电压")
	private Double batteryVoltage;

	@ApiModelProperty(value = "数据产生时间")
	private Date dataTime;

	@ApiModelProperty(value = "数据库中记录生成时间")
	private Date createDate;

}
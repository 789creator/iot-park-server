package com.szzt.iot.admin.modules.device.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 告警设备短信接收记录
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-23
 */
@Data
@ApiModel(value = "告警设备短信接收记录")
public class DeviceNoticeSmsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "发件人号码")
	private String fromUser;

	@ApiModelProperty(value = "短信接收时间")
	private String receiveDate;

	@ApiModelProperty(value = "短信接收内容")
	private String content;


}
package com.szzt.iot.admin.modules.device.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-17
 */
@Data
@ApiModel(value = "")
public class DeviceNoticeResultJsonDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "通知处理结束时间")
	private String time;

	@ApiModelProperty(value = "通知处理方式：电话、短信、邮件、广播")
	private String method;

	@ApiModelProperty(value = "通知处理结果状态：失败、成功、接通未确认、接通已确认")
	private String status;

	@ApiModelProperty(value = "联系人号码、邮件地址、音频广播以“-----------”替代")
	private String to;

	@ApiModelProperty(value = "通知内容")
	private String notice;


}
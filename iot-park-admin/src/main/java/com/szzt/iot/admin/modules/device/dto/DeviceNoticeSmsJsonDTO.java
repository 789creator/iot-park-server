package com.szzt.iot.admin.modules.device.dto;

import lombok.Data;


/**
 * 告警设备短信接收记录 json bean
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-23
 */
@Data
public class DeviceNoticeSmsJsonDTO {
	private String From;
	private String Date;
	private String Content;


}
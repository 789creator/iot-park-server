package com.szzt.iot.admin.modules.device.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 告警设备短信接收记录
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("device_notice_sms")
public class DeviceNoticeSmsEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@TableId
	private Long id;
    /**
     * 发件人号码
     */
	private String fromUser;
    /**
     * 短信接收时间
     */
	private String receiveDate;
    /**
     * 短信接收内容
     */
	private String content;
}
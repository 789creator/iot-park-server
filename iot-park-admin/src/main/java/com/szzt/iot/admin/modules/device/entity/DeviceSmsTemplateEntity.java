package com.szzt.iot.admin.modules.device.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.szzt.iot.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 告警设备通知内容模板
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("device_sms_template")
public class DeviceSmsTemplateEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板内容
     */
    private String templateContent;
    /**
     * 是否默认模板，1：是，0：否
     */
    private Integer isDefault;
}
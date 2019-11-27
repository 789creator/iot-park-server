package com.szzt.iot.admin.modules.device.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author szzt ${email}
 * @since 1.0.0 2019-10-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("device_notice_result")
public class DeviceNoticeResultEntity {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 通知处理结束时间
     */
    private String time;
    /**
     * 通知处理方式：电话、短信、邮件、广播
     */
    private String method;
    /**
     * 通知处理结果状态：失败、成功、接通未确认、接通已确认
     */
    private String status;
    /**
     * 联系人号码、邮件地址、音频广播以“-----------”替代
     */
    private String toUser;
    /**
     * 通知内容
     */
    private String notice;
}
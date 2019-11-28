package com.szzt.iot.admin.modules.device.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 烟雾报警数据
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("device_smoke_alarm")
public class DeviceSmokeAlarmEntity {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 备注名称
     */
    private String deviceName;
    /**
     * 状态/启用状态
     */
    private Integer deviceStatus;
    /**
     * 电池电压
     */
    private Double batteryVoltage;
    /**
     * 数据产生时间
     */
    private Date dataTime;
    /**
     * 创建时间
     */
    private Date createDate;
}
package com.szzt.iot.transfer.db.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
public class DeviceSmokeAlarmEntity implements Serializable {
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
     * 设备数据生成时间戳
     */
    private Date eventTime;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 设备唯一标示
     */
    private String iotId;
    /**
     * 产品唯一标示
     */
    private String productKey;
    /**
     * 设备类型
     */
    private String type;
    /**
     * 设备类型
     */
    private Integer deviceType;
    /**
     * 烟雾检测状态
     */
    private Integer smokeSensorState;


}
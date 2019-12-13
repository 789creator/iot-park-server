package com.szzt.iot.common.websocket.body;

import lombok.Data;

import java.io.Serializable;

/**
 * 烟雾报警 msg body
 *
 * @author zhouhongjin
 */
@Data
public class SmokeAlarmWebsocketBody implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 电池电压
     */
    private Double batteryVoltage;
    /**
     * 设备类型 2：烟雾探测器
     */
    private Integer deviceType;
    /**
     * 设备状态码
     * 0 - 正常状态
     * 1 - 火警
     * 2 - 传感器故障
     * 3 - 电池低电压
     * 4 - 火警解除
     * 5 - 传感器故障解除
     * 6 - 电池低电压解除
     * 13 - 设备自检
     * 14 - 设备上电
     */
    private Integer deviceStatus;

    private String SmokeStatus;

}

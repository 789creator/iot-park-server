package com.szzt.iot.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class SmokeDetectorEntity implements Serializable {

    //设备ID（旧版参数）
    @ApiModelProperty(value = "设备ID（旧版参数）")
    private String DeviceId;

    //设备名称
    @ApiModelProperty(value = "设备名称")
    private String DeviceName;

    //设备所属产品Key
    @ApiModelProperty(value = "设备所属产品Key")
    private String ProductKey;

    //设备创建GMT时间
    @ApiModelProperty(value = "设备创建GMT时间")
    private Date GmtGreate;

    //设备信息修改GMT时间
    @ApiModelProperty(value = "设备信息修改GMT时间")
    private Date GmtModified;

    //设备状态
    @ApiModelProperty(value = "设备状态")
    private String DeviceStatus;

    //设备ID
    @ApiModelProperty(value = "设备ID")
    private String IotId;

    //设备的备注名称
    @ApiModelProperty(value = "设备的备注名称")
    private String Nickname;

}

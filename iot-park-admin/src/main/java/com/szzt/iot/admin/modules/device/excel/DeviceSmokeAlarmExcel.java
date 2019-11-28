package com.szzt.iot.admin.modules.device.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 烟雾报警数据
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-11-28
 */
@Data
public class DeviceSmokeAlarmExcel {
    @Excel(name = "")
    private Long id;
    @Excel(name = "备注名称")
    private String deviceName;
    @Excel(name = "节点类型")
    private Integer deviceType;
    @Excel(name = "状态/启用状态")
    private Integer deviceStatus;
    @Excel(name = "电池电压")
    private Double batteryVoltage;
    @Excel(name = "数据产生时间")
    private Date dataTime;
    @Excel(name = "数据库中记录生成时间")
    private Date createTime;

}
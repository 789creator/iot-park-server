package com.szzt.iot.admin.modules.device.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 告警设备短信接收记录
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-23
 */
@Data
public class DeviceNoticeSmsExcel {
    @Excel(name = "")
    private Long id;
    @Excel(name = "发件人号码")
    private String fromUser;
    @Excel(name = "短信接收时间")
    private String receiveDate;
    @Excel(name = "短信接收内容")
    private String content;

}
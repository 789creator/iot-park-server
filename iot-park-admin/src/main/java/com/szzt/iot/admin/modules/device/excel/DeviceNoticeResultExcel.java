package com.szzt.iot.admin.modules.device.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-17
 */
@Data
public class DeviceNoticeResultExcel {
    @Excel(name = "")
    private Long id;
    @Excel(name = "通知处理结束时间")
    private String time;
    @Excel(name = "通知处理方式：电话、短信、邮件、广播")
    private String method;
    @Excel(name = "通知处理结果状态：失败、成功、接通未确认、接通已确认")
    private String status;
    @Excel(name = "联系人号码、邮件地址、音频广播以“-----------”替代")
    private String to;
    @Excel(name = "通知内容")
    private String notice;

}
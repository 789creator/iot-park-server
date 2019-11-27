package com.szzt.iot.admin.modules.device.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 告警设备通知内容模板
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-28
 */
@Data
public class DeviceSmsTemplateExcel {
    @Excel(name = "")
    private Long id;
    @Excel(name = "模板名称")
    private String templateName;
    @Excel(name = "模板内容")
    private String templateContent;
    @Excel(name = "创建时间")
    private Date createDate;
    @Excel(name = "创建者")
    private String creator;

}
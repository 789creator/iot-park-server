package com.szzt.iot.admin.modules.sys.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 系统配置
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-11-29
 */
@Data
public class SysConfigExcel {
    @Excel(name = "")
    private Long id;
    @Excel(name = "key")
    private String key;
    @Excel(name = "value")
    private String value;

}
package com.szzt.iot.transfer.config;

import com.szzt.iot.admin.modules.sys.service.SysConfigService;
import com.szzt.iot.common.utils.SpringContextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统配置参数
 *
 * @author zhouhongjin
 */
public class SysParamConfig {
    public static Map<String, String> paramMap = new HashMap<String, String>();

    public static String getValue(String key) {
        if (paramMap.isEmpty()) {
            // 从数据库加载配置文件
            SysConfigService sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigServiceImpl");
            Map resultMap = sysConfigService.getAllConfig();
            paramMap.putAll(resultMap);
        }
        return paramMap.get(key);
    }

    /**
     *
     * @return
     */
    public static String getAccessKey() {
        return getValue("accessKey");
    }

    public static String getAccessSecret() {
        return getValue("accessSecret");
    }

    public static String getSmokeAlarmConsumerGroupId() {
        return getValue("smokeAlarmConsumerGroupId");
    }

    public static String getUid() {
        return getValue("uid");
    }

    public static String getRegionId() {
        return getValue("regionId");
    }
}

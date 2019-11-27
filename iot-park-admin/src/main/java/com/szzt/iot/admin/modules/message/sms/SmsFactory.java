/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.message.sms;

import com.szzt.iot.admin.modules.sys.service.SysParamsService;
import com.szzt.iot.common.constant.Constant;
import com.szzt.iot.common.utils.SpringContextUtils;

/**
 * 短信Factory
 *
 * @author
 */
public class SmsFactory {
    private static SysParamsService sysParamsService;

    static {
        SmsFactory.sysParamsService = SpringContextUtils.getBean(SysParamsService.class);
    }

    public static AbstractSmsService build(){
        //获取短信配置信息
        SmsConfig config = sysParamsService.getValueObject(Constant.SMS_CONFIG_KEY, SmsConfig.class);

        if(config.getPlatform() == Constant.SmsService.ALIYUN.getValue()){
            return new AliyunSmsService(config);
        }else if(config.getPlatform() == Constant.SmsService.QCLOUD.getValue()){
            return new QcloudSmsService(config);
        }

        return null;
    }
}

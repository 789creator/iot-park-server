/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.message.service;

import com.szzt.iot.admin.modules.message.dto.SysSmsDTO;
import com.szzt.iot.admin.modules.message.entity.SysSmsEntity;
import com.szzt.iot.common.page.PageData;
import com.szzt.iot.common.service.BaseService;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 短信
 *
 * @author
 */
public interface SysSmsService extends BaseService<SysSmsEntity> {

    PageData<SysSmsDTO> page(Map<String, Object> params);

    /**
     * 发送短信
     * @param mobile   手机号
     * @param params   短信参数
     */
    void send(String mobile, String params);

    /**
     * 保存短信发送记录
     * @param platform   平台
     * @param mobile   手机号
     * @param params   短信参数
     * @param status   发送状态
     */
    void save(Integer platform, String mobile, LinkedHashMap<String, String> params, Integer status);
}


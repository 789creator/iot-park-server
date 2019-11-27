/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.message.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szzt.iot.admin.modules.message.dao.SysMailTemplateDao;
import com.szzt.iot.admin.modules.message.dto.SysMailTemplateDTO;
import com.szzt.iot.admin.modules.message.email.EmailUtils;
import com.szzt.iot.common.exception.ErrorCode;
import com.szzt.iot.common.exception.RobotException;
import com.szzt.iot.common.service.impl.CrudServiceImpl;
import com.szzt.iot.admin.modules.message.entity.SysMailTemplateEntity;
import com.szzt.iot.admin.modules.message.service.SysMailTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysMailTemplateServiceImpl extends CrudServiceImpl<SysMailTemplateDao, SysMailTemplateEntity, SysMailTemplateDTO> implements SysMailTemplateService {
    @Autowired
    private EmailUtils emailUtils;

    @Override
    public QueryWrapper<SysMailTemplateEntity> getWrapper(Map<String, Object> params) {
        String name = (String)params.get("name");

        QueryWrapper<SysMailTemplateEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name);

        return wrapper;
    }

    @Override
    public boolean sendMail(Long id, String mailTo, String mailCc, String params) throws Exception{
        Map<String, Object> map = null;
        try {
            if(StringUtils.isNotEmpty(params)){
                map = JSON.parseObject(params, Map.class);
            }
        }catch (Exception e){
            throw new RobotException(ErrorCode.JSON_FORMAT_ERROR);
        }
        String[] to = new String[]{mailTo};
        String[] cc = StringUtils.isBlank(mailCc) ? null : new String[]{mailCc};

        return emailUtils.sendMail(id, to, cc, map);
    }

    @Override
    public boolean receive() throws Exception {
        emailUtils.receiveEmail();
        return true;
    }
}

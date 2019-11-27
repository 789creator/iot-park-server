/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.message.service;

import com.szzt.iot.admin.modules.message.dto.SysMailTemplateDTO;
import com.szzt.iot.admin.modules.message.entity.SysMailTemplateEntity;
import com.szzt.iot.common.service.CrudService;

/**
 * 邮件模板
 *
 * @author
 */
public interface SysMailTemplateService extends CrudService<SysMailTemplateEntity, SysMailTemplateDTO> {

    /**
     * 发送邮件
     * @param id           邮件模板ID
     * @param mailTo       收件人
     * @param mailCc       抄送
     * @param params       模板参数
     */
    boolean sendMail(Long id, String mailTo, String mailCc, String params) throws Exception;

    boolean receive() throws Exception;

}
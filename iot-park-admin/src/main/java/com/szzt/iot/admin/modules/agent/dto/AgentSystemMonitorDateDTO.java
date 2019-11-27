package com.szzt.iot.admin.modules.agent.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author szzt szzt@szzt.com
 * @since 1.0.0 2019-07-17
 */
@Data
public class AgentSystemMonitorDateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    private Date createTime;

    private String value;


}
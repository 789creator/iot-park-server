package com.szzt.iot.admin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;


/**
 * 服务器信息表
 *
 * @author.process.szzt.process.com
 * @since 1.0.0 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("agent_server")
public class AgentServerEntity {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    /**
     * 服务器名
     */
    private String serverMachineName;
    /**
     * 服务器IP
     */
    private String serverIp;
    /**
     * 服务器版本
     */
    private String serverVersion;
    /**
     * 服务器编号
     */
    private String code;
    /**
     * 服务器状态 0：空闲 1：占用
     */
    private Integer status;
    /**
     * 是否在线0：在线 1：在线
     */
    private Integer isOnline;
}
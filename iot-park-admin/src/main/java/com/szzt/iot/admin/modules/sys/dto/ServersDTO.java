package com.szzt.iot.admin.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 服务器信息表
 *
 * @author.process.szzt.process.com
 * @since 1.0.0 2019-06-17
 */
@Data
@ApiModel(value = "服务器信息表")
public class ServersDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "服务器名")
	private String serverMachineName;

	@ApiModelProperty(value = "服务器IP")
	private String serverIp;

	@ApiModelProperty(value = "服务器版本")
	private String serverVersion;

	@ApiModelProperty(value = "服务器编号")
	private String code;

	@ApiModelProperty(value = "服务器状态 0：空闲 1：占用")
	private Integer status;

	@ApiModelProperty(value = "服务器在线状态 0：不在线 1：在线")
	private Integer isOnline;


}
package com.szzt.iot.admin.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统配置
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-11-29
 */
@Data
@ApiModel(value = "系统配置")
public class SysConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "key")
	private String paramKey;

	@ApiModelProperty(value = "value")
	private String paramValue;

	@ApiModelProperty(value = "说明")
	private String description;

}
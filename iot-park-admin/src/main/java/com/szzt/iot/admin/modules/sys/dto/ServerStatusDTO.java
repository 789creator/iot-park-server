package com.szzt.iot.admin.modules.sys.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServerStatusDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer status;

    private Integer num;


}

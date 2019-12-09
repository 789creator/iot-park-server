package com.szzt.iot.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class Page {
    //总条数
    @ApiModelProperty(value = "总条数")
    private Integer Total;
    //总页数
    @ApiModelProperty(value = "总页数")
    private Integer PageCount;
    //当前页数据
    @ApiModelProperty(value = "当前页数据")
    private List<SmokeDetectorEntity> list;
}

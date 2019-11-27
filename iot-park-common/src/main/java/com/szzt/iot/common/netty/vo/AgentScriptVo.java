package com.szzt.iot.common.netty.vo;

import lombok.Data;

import java.util.Date;

/**
 *  与AgentScriptEntity 字段保持一致
 */
@Data
public class AgentScriptVo  {

    private Long id;
    /**
     *  脚本所属分类
     */
    private Long scriptTypeId;
    /**
     *  脚本名称
     */
    private String scriptName;
    /**
     *  脚本路径（相对路径）
     */
    private String scriptPath;
    /**
     *  脚本语言类型（Python/Sikulix/Selenium）
     */
    private String scriptLanguageType;
    /**
     *  脚本创建人id
     */
    private Long creator;
    /**
     *  脚本业务说明
     */
    private String scriptDesc;
    /**
     *  更新人id
     */
    private Long updater;
    /**
     *  创建时间
     */
    private Date createDate;

}

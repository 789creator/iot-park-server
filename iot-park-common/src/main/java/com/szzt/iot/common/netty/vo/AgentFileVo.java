package com.szzt.iot.common.netty.vo;

import lombok.Data;

import java.util.Date;

/**
 *  文件同步到agent vo
 * @author zhouhongjin
 */
@Data
public class AgentFileVo {

    /**
     *  文件相对路径
     */
    private String fileRelativePath;
    /**
     *  同步完成之后，是否对文件进行解压
     */
    private Boolean unzip;


}

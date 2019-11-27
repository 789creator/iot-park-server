package com.szzt.iot.admin.modules.netty.server.manager;

import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

/**
 * 客户端连接信息
 * @author zhouhongjin
 */
@Data
public class ClientConnectionInfo {
    /**
     * 等于channelId的值
     */
    private String id;
    private String name;
    private int heartBeatCheckNum;
    private ChannelHandlerContext channelHandlerContext;
}

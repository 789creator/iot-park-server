package com.szzt.iot.admin.modules.netty.server.manager;

import lombok.Data;

import java.net.SocketAddress;

/**
 * 客户端info
 *
 * @author zhouhongjin
 */
@Data
public class ClientInfo {
    private String name;
    private SocketAddress socketAddress;
}

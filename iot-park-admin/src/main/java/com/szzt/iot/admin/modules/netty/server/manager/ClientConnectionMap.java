package com.szzt.iot.admin.modules.netty.server.manager;

import cn.hutool.core.util.StrUtil;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 客户端连接集合
 *
 * @author zhouhongjin
 */
public class ClientConnectionMap {
    /**
     * 客户端连接 map,
     */
    public static Map<String, ClientConnectionInfo> connectionMap = new ConcurrentHashMap();
    /**
     * 客户端info map
     */
    public static Map<String, Object> infoMap = new ConcurrentHashMap();
    /**
     * 客户端ip与channelId 之间的 map， key为clientIp，value为channelId
     */
    public static Map<String, String> clientIpMap = new ConcurrentHashMap();

    /**
     * 保存客户端连接相关的信息
     *
     * @param ctx
     */
    public static void addClientConnection(ChannelHandlerContext ctx) {
        ClientConnectionInfo clientConnectionInfo = new ClientConnectionInfo();
        String channelId = ctx.channel().id().asLongText();
        clientConnectionInfo.setId(channelId);
        clientConnectionInfo.setName(ctx.name());
        clientConnectionInfo.setChannelHandlerContext(ctx);
        clientConnectionInfo.setHeartBeatCheckNum(0);
        connectionMap.put(channelId, clientConnectionInfo);
        ClientInfo clientInfo = new ClientInfo();
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        clientInfo.setName(ctx.name());
        clientInfo.setSocketAddress(socketAddress);
        infoMap.put(channelId, clientInfo);
        clientIpMap.put(socketAddress.getAddress().getHostAddress(), channelId);
    }

    public static ClientConnectionInfo getClientConnectionInfoByClientIp(String clientIp) {
        String channelId = clientIpMap.get(clientIp);
        if (StrUtil.isEmpty(channelId)) {
            return null;
        }
        return connectionMap.get(channelId);
    }

    /**
     * 移除客户端 相关的
     * @param clientConnectionInfo
     */
    public static void removeClient(ClientConnectionInfo clientConnectionInfo) {
        //移除客户端信息
        infoMap.remove(clientConnectionInfo.getId());
        //移除clientIpMap
        String clientIp = ((InetSocketAddress)clientConnectionInfo.getChannelHandlerContext().channel().remoteAddress()).getAddress().getHostAddress();
        clientIpMap.remove(clientIp);
    }
}

package com.szzt.iot.admin.modules.netty.server.threadworkers;

import java.io.Serializable;

/**
 * 一个客户端请求所对应的所有有用数据对想
 */
public class Server2WebClientData implements Serializable{
	private static final long serialVersionUID = -7164069554228806843L;
	/**
	 * 通道的远程ip地址
	 */
	private String ipAddress;

	
}

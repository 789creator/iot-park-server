package com.szzt.iot.admin.modules.netty.server.annotation;

import com.szzt.iot.admin.modules.netty.server.config.NettyServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * netty服务端启动注解
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(NettyServerConfig.class)
public @interface EnableNettyServer {

}

/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 * <p>
 * https://www.szzt.com.cn
 * <p>
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin;

import com.szzt.iot.common.dynamic.datasource.annotation.EnableDynamicDataSource;
import com.szzt.iot.admin.modules.netty.server.annotation.EnableNettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


/**
 * iot-admin
 *
 * @author
 */
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@EnableNettyServer
@EnableDynamicDataSource
@ComponentScan("com.szzt.iot")
public class AdminApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AdminApplication.class);
    }
}
/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.transfer;

import com.szzt.iot.common.dynamic.datasource.annotation.EnableDynamicDataSource;
import com.szzt.iot.transfer.amqp.annotation.EnableAmqpConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * transfer
 *
 * @author
 */
@SpringBootApplication
@EnableDynamicDataSource
@ComponentScan("com.szzt.iot")
//@EnableAmqpConsumer
public class TransferApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TransferApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TransferApplication.class);
	}
}

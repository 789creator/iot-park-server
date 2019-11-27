package com.szzt.iot.common.dynamic.datasource.annotation;

import com.szzt.iot.common.dynamic.datasource.config.DynamicDataSourceConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用多数据源注解（没有多数据配置文件，仅启动默认数据库连接）
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(DynamicDataSourceConfig.class)
public @interface EnableDynamicDataSource {
}

/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.common.exception;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.szzt.iot.common.exception.ErrorCode;
import com.szzt.iot.common.exception.ExceptionUtils;
import com.szzt.iot.common.exception.IotException;
import com.szzt.iot.common.utils.HttpContextUtils;
import com.szzt.iot.common.utils.IpUtils;
import com.szzt.iot.common.utils.Result;
import com.szzt.iot.admin.modules.log.entity.SysLogErrorEntity;
import com.szzt.iot.admin.modules.log.service.SysLogErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 异常处理器
 *
 * @author
 * @since 1.0.0
 */
@RestControllerAdvice
public class RobotExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(RobotExceptionHandler.class);

	@Autowired
	private SysLogErrorService sysLogErrorService;

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(IotException.class)
	public Result handleRobotException(IotException ex){
		Result result = new Result();
		result.error(ex.getCode(), ex.getMsg());

		return result;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Result handleDuplicateKeyException(DuplicateKeyException ex){
		Result result = new Result();
		result.error(ErrorCode.DB_RECORD_EXISTS);

		return result;
	}

	@ExceptionHandler(Exception.class)
	public Result handleException(Exception ex){
		logger.error(ex.getMessage(), ex);

		saveLog(ex);

		return new Result().error(ex.getMessage());
	}

	/**
	 * 保存异常日志
	 */
	private void saveLog(Exception ex){
		SysLogErrorEntity log = new SysLogErrorEntity();

		//请求相关信息
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		log.setIp(IpUtils.getIpAddr(request));
		log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
		log.setRequestUri(request.getRequestURI());
		log.setRequestMethod(request.getMethod());
		Map<String, String> params = HttpContextUtils.getParameterMap(request);
		if(MapUtil.isNotEmpty(params)){
			log.setRequestParams(JSON.toJSONString(params));
		}

		//异常信息
		log.setErrorInfo(ExceptionUtils.getErrorStackTrace(ex));

		//保存
		sysLogErrorService.save(log);
	}
}
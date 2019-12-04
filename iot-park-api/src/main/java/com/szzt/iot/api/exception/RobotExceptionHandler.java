/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.api.exception;

import com.szzt.iot.common.exception.ErrorCode;
import com.szzt.iot.common.exception.IotException;
import com.szzt.iot.common.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author
 * @since 1.0.0
 */
@RestControllerAdvice
public class RobotExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(RobotExceptionHandler.class);

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

		return new Result().error();
	}
}
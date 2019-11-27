/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.szzt.iot.admin.modules.job.dao.ScheduleJobLogDao;
import com.szzt.iot.admin.modules.job.dto.ScheduleJobLogDTO;
import com.szzt.iot.common.constant.Constant;
import com.szzt.iot.common.page.PageData;
import com.szzt.iot.common.service.impl.BaseServiceImpl;
import com.szzt.iot.common.utils.ConvertUtils;
import com.szzt.iot.admin.modules.job.entity.ScheduleJobLogEntity;
import com.szzt.iot.admin.modules.job.service.ScheduleJobLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ScheduleJobLogServiceImpl extends BaseServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {

	@Override
	public PageData<ScheduleJobLogDTO> page(Map<String, Object> params) {
		IPage<ScheduleJobLogEntity> page = baseDao.selectPage(
			getPage(params, Constant.CREATE_DATE, false),
			getWrapper(params)
		);
		return getPageData(page, ScheduleJobLogDTO.class);
	}

	private QueryWrapper<ScheduleJobLogEntity> getWrapper(Map<String, Object> params){
		String jobId = (String)params.get("jobId");

		QueryWrapper<ScheduleJobLogEntity> wrapper = new QueryWrapper<>();
		wrapper.eq(StringUtils.isNotBlank(jobId), "job_id", jobId);

		return wrapper;
	}

	@Override
	public ScheduleJobLogDTO get(Long id) {
		ScheduleJobLogEntity entity = baseDao.selectById(id);

		return ConvertUtils.sourceToTarget(entity, ScheduleJobLogDTO.class);
	}

}
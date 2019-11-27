package com.szzt.iot.admin.modules.agent.dao;

import com.szzt.iot.admin.modules.agent.entity.AgentSystemMonitorEntity;
import com.szzt.iot.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统监控表（会定时删除这个表的数据）
 *
 * @author szzt szzt@szzt.com
 * @since 1.0.0 2019-07-17
 */
@Mapper
public interface AgentSystemMonitorDao extends BaseDao<AgentSystemMonitorEntity> {
}
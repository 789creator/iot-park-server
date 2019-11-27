package com.szzt.iot.admin.modules.agent.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szzt.iot.admin.modules.agent.dto.AgentSystemMonitorDateDTO;
import com.szzt.iot.admin.modules.agent.entity.AgentSystemMonitorHistoryEntity;
import com.szzt.iot.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统监控表（会定时删除这个表的数据）
 *
 * @author szzt szzt@szzt.com
 * @since 1.0.0 2019-07-17
 */
@Mapper
public interface AgentSystemMonitorHistoryDao extends BaseDao<AgentSystemMonitorHistoryEntity> {

    /**
     * 删除数据库记录创建日期小于date的数据
     *
     * @param date
     */
    void deleteBeforeDate(String date);

    Page<AgentSystemMonitorDateDTO> pageCpu(Page<AgentSystemMonitorDateDTO> page, @Param("serverIp") String serverIp, @Param("startTime") String startTime, @Param("endTime") String endTime);

    Page<AgentSystemMonitorDateDTO> pageMemory(Page<AgentSystemMonitorDateDTO> page, @Param("serverIp") String serverIp, @Param("startTime") String startTime, @Param("endTime") String endTime);
}
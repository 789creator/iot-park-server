package com.szzt.iot.admin.modules.agent.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szzt.iot.admin.modules.agent.dao.AgentSystemMonitorHistoryDao;
import com.szzt.iot.admin.modules.agent.dto.AgentSystemMonitorDateDTO;
import com.szzt.iot.admin.modules.agent.dto.AgentSystemMonitorHistoryDTO;
import com.szzt.iot.admin.modules.agent.entity.AgentSystemMonitorHistoryEntity;
import com.szzt.iot.admin.modules.agent.service.AgentSystemMonitorHistoryService;
import com.szzt.iot.common.activemq.system.SystemMonitorBody;
import com.szzt.iot.common.constant.Constant;
import com.szzt.iot.common.service.impl.CrudServiceImpl;
import com.szzt.iot.common.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 系统监控历史记录
 *
 * @author szzt szzt@szzt.com
 * @since 1.0.0 2019-07-17
 */
@Service
public class AgentSystemMonitorHistoryServiceImpl extends CrudServiceImpl<AgentSystemMonitorHistoryDao, AgentSystemMonitorHistoryEntity, AgentSystemMonitorHistoryDTO> implements AgentSystemMonitorHistoryService {

    @Override
    public QueryWrapper<AgentSystemMonitorHistoryEntity> getWrapper(Map<String, Object> params) {
        String serverIp = (String) params.get("serverIp");

        QueryWrapper<AgentSystemMonitorHistoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(serverIp), "server_ip", serverIp);

        return wrapper;
    }

    @Override
    public void saveSystemMonitor(SystemMonitorBody body) {
        AgentSystemMonitorHistoryEntity agentSystemMonitorHistoryEntity = new AgentSystemMonitorHistoryEntity();
        BeanUtil.copyProperties(body, agentSystemMonitorHistoryEntity);
        agentSystemMonitorHistoryEntity.setCreateTime(new Date());
        this.insert(agentSystemMonitorHistoryEntity);
    }

    @Override
    public void deleteBeforeDate(String date) {
        baseDao.deleteBeforeDate(date);
    }

    @Override
    public Result pageCpu(Map<String, Object> params) {
        //分页参数
        long curPage = 1;
        long limit = 10;

        if (params.get(Constant.PAGE) != null) {
            curPage = Long.parseLong((String) params.get(Constant.PAGE));
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Long.parseLong((String) params.get(Constant.LIMIT));
        }

        //分页对象
        Page<AgentSystemMonitorDateDTO> page = new Page<>(curPage, limit);

        String serverIp = (String) params.get("serverIp");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        page = baseDao.pageCpu(page, serverIp,startTime,endTime);

        return new Result().ok(page);
    }

    @Override
    public Result pageMemory(Map<String, Object> params) {
        //分页参数
        long curPage = 1;
        long limit = 10;

        if (params.get(Constant.PAGE) != null) {
            curPage = Long.parseLong((String) params.get(Constant.PAGE));
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Long.parseLong((String) params.get(Constant.LIMIT));
        }

        //分页对象
        Page<AgentSystemMonitorDateDTO> page = new Page<>(curPage, limit);

        String serverIp = (String) params.get("serverIp");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        page = baseDao.pageMemory(page, serverIp,startTime,endTime);

        return new Result().ok(page);
    }
}
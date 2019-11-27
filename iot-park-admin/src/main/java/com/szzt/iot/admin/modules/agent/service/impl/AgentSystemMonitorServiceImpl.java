package com.szzt.iot.admin.modules.agent.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szzt.iot.admin.modules.agent.dao.AgentSystemMonitorDao;
import com.szzt.iot.admin.modules.agent.dto.AgentSystemMonitorDTO;
import com.szzt.iot.admin.modules.agent.entity.AgentSystemMonitorEntity;
import com.szzt.iot.admin.modules.agent.service.AgentSystemMonitorService;
import com.szzt.iot.common.activemq.system.SystemMonitorBody;
import com.szzt.iot.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 系统监控表（会定时删除这个表的数据）
 *
 * @author szzt szzt@szzt.com
 * @since 1.0.0 2019-07-17
 */
@Service
public class AgentSystemMonitorServiceImpl extends CrudServiceImpl<AgentSystemMonitorDao, AgentSystemMonitorEntity, AgentSystemMonitorDTO> implements AgentSystemMonitorService {

    @Override
    public QueryWrapper<AgentSystemMonitorEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<AgentSystemMonitorEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public void updateAgent(SystemMonitorBody body) {
        QueryWrapper<AgentSystemMonitorEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("server_ip", body.getServerIp());
        AgentSystemMonitorEntity agentSystemMonitorEntity = baseDao.selectOne(wrapper);
        if (agentSystemMonitorEntity == null) {
            AgentSystemMonitorEntity entity = new AgentSystemMonitorEntity();
            BeanUtil.copyProperties(body, entity);
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            baseDao.insert(entity);
        } else {
            BeanUtil.copyProperties(body, agentSystemMonitorEntity, "server_ip", "create_time");
            agentSystemMonitorEntity.setUpdateTime(new Date());
            baseDao.updateById(agentSystemMonitorEntity);
        }
    }
}
package com.szzt.iot.admin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szzt.iot.admin.modules.sys.dao.AgentServerDao;
import com.szzt.iot.admin.modules.sys.dto.ServerStatusDTO;
import com.szzt.iot.admin.modules.sys.dto.ServersDTO;
import com.szzt.iot.admin.modules.sys.entity.AgentServerEntity;
import com.szzt.iot.admin.modules.sys.service.AgentServerService;
import com.szzt.iot.common.service.impl.BaseServiceImpl;
import com.szzt.iot.common.utils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AgentServerServiceImpl extends BaseServiceImpl<AgentServerDao, AgentServerEntity> implements AgentServerService {

    @Autowired
    AgentServerDao agentServerDao;


    @Override
    public IPage<AgentServerEntity> list(Map<String, Object> params) {
        String status = (String)params.get("status");
        Integer pageNo = params.get("pageNo") == null?1:(Integer) params.get("pageNo");
        Integer pageSize = params.get("pageSize") == null ?10:(Integer) params.get("pageSize");

        Page<AgentServerEntity> page = new Page<>(pageNo, pageSize);

        QueryWrapper<AgentServerEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(status),"status", status);
        IPage<AgentServerEntity> entityList = baseDao.selectPage(page, wrapper);

        return entityList;

    }

    @Override
    public ServersDTO get(Long id) {
        AgentServerEntity entity = baseDao.selectById(id);

        ServersDTO dto = ConvertUtils.sourceToTarget(entity, ServersDTO.class);

        return dto;
    }

    @Override
    public void save(ServersDTO dto) {
        AgentServerEntity entity = ConvertUtils.sourceToTarget(dto, AgentServerEntity.class);
        insert(entity);
    }

    @Override
    public void update(ServersDTO dto) {
        AgentServerEntity entity = ConvertUtils.sourceToTarget(dto, AgentServerEntity.class);
        updateById(entity);
    }

    @Override
    public void delete(Long id) {
        deleteById(id);
    }

    @Override
    public List<AgentServerEntity> selectFreeServer() {
        Map<String, Object> params = new HashMap<>();
        params.put("is_online", 1);
        params.put("status", 0);
        return baseDao.selectByMap(params);

    }

    @Override
    public void updateServerStatus(String agentIp, int isOnline) {
        AgentServerEntity serversEntity = new AgentServerEntity();
        serversEntity.setIsOnline(isOnline);
        UpdateWrapper<AgentServerEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("server_ip",agentIp);
        this.update(serversEntity,wrapper);
//        QueryWrapper<ServersEntity> wrapper = new QueryWrapper<>();
//        wrapper.eq("server_ip", agentIp);
//        ServersEntity serversEntity = baseDao.selectOne(wrapper);
//        serversEntity.setIsOnline(isOnline);
//        this.updateById(serversEntity);
    }

    @Override
    public List<AgentServerEntity> getAllAgentServer() {
        QueryWrapper<AgentServerEntity> wrapper = new QueryWrapper<>();
        return baseDao.selectList(wrapper);
    }

    @Override
    public void updateServer(String serverIp, int status) {
        AgentServerEntity serversEntity = new AgentServerEntity();
        serversEntity.setStatus(status);
        UpdateWrapper<AgentServerEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("server_ip",serverIp);
        baseDao.update(serversEntity,wrapper);
    }

    @Override
    public List<ServerStatusDTO> getServerStatus() {

        List<ServerStatusDTO> list = new ArrayList<>();
        ServerStatusDTO emptyServer = agentServerDao.getServerStatus(0);
        ServerStatusDTO busyServer = agentServerDao.getServerStatus(1);
        list.add(emptyServer);
        list.add(busyServer);

        return list;
    }

    public QueryWrapper<AgentServerEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<AgentServerEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }
}

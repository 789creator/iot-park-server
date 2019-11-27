package com.szzt.iot.admin.modules.sys.dao;

import com.szzt.iot.admin.modules.sys.entity.AgentServerEntity;
import com.szzt.iot.common.dao.BaseDao;
import com.szzt.iot.admin.modules.sys.dto.ServerStatusDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 服务器信息表
 *
 * @author.process.szzt.process.com
 * @since 1.0.0 2019-06-17
 */
@Mapper
public interface AgentServerDao extends BaseDao<AgentServerEntity> {

    void getAllAgentServer();

    ServerStatusDTO getServerStatus(@Param("status")Integer status);
}
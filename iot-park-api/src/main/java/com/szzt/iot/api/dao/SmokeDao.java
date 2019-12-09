package com.szzt.iot.api.dao;

import com.szzt.iot.api.entity.DeviceSmokeEntity;
import com.szzt.iot.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmokeDao extends BaseDao<DeviceSmokeEntity> {
    //根据设备id查询设备状态为火警（4）的数据列表
    List<DeviceSmokeEntity> List(Map<String, Object> param);
}

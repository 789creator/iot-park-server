package com.szzt.iot.api.service;

import com.szzt.iot.api.entity.DeviceSmokeEntity;
import com.szzt.iot.api.entity.Page;
import com.szzt.iot.common.page.PageData;
import com.szzt.iot.common.service.BaseService;


import java.util.Map;


public interface SmokeDetectorService extends BaseService<DeviceSmokeEntity> {
    //查询烟感器分页列表
    Page page(Integer CurrentPage, Integer PageSize) ;

    //根据设备id查询设备状态为火警（4）的数据列表
    PageData<DeviceSmokeEntity> smokePage(Map<String, Object> param);
}

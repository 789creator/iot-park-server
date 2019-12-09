package com.szzt.iot.api.service.impl;


import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.QueryDeviceRequest;
import com.aliyuncs.iot.model.v20180120.QueryDeviceResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.szzt.iot.api.dao.SmokeDao;
import com.szzt.iot.api.entity.DeviceSmokeEntity;
import com.szzt.iot.api.entity.Page;
import com.szzt.iot.api.entity.SmokeDetectorEntity;
import com.szzt.iot.api.service.SmokeDetectorService;
import com.szzt.iot.common.constant.Constant;
import com.szzt.iot.common.page.PageData;
import com.szzt.iot.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SmokeDetectorServiceImpl extends BaseServiceImpl<SmokeDao, DeviceSmokeEntity> implements SmokeDetectorService {

    private final static Logger logger = LoggerFactory.getLogger(SmokeDetectorServiceImpl.class);
    //查询烟感器分页列表
    @Override
    public Page page(Integer CurrentPage, Integer PageSize)  {
        DefaultAcsClient client = null;
        try {
            //初始化客户端
            String  accessKeyId = "LTAI4FgPrS8g4wnkcZqCpY7Q";
            String  secret = "XP2TRvbvrRN3EtoccZ3T8Z4zSBDz3W";
            String  regionId = "cn-shanghai";
            String domain = "iot.cn-shanghai.aliyuncs.com"; //host: http://popunify-share.cn-shanghai.aliyuncs.com

            DefaultProfile.addEndpoint(regionId,regionId,"Iot",domain);
            DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
            client = new DefaultAcsClient(profile);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        //设置设备请求参数
        QueryDeviceRequest request = new QueryDeviceRequest();
        //产品暂时写死
        request.setProductKey("a1tNnA8UI3i");
        request.setCurrentPage(CurrentPage);
        request.setPageSize(PageSize);


        SmokeDetectorEntity smoke = new SmokeDetectorEntity();
        ArrayList<SmokeDetectorEntity> smkeList = new ArrayList<>();
        Page page = new Page();
        try {
            QueryDeviceResponse acsResponse = client.getAcsResponse(request);
            if (acsResponse.getSuccess()!=null&&acsResponse.getSuccess()){
                logger.info("产品下设备列表查询成功");
                logger.info(JSON.toJSONString(acsResponse));
            }else {
                logger.info("产品下设备列表查询失败");
                logger.error(JSON.toJSONString(acsResponse));
            }

            List<QueryDeviceResponse.DeviceInfo> data = acsResponse.getData();
            for (QueryDeviceResponse.DeviceInfo datum : data) {
                smoke.setIotId(datum.getIotId());
                smoke.setDeviceId(datum.getDeviceId());
                smoke.setDeviceName(datum.getDeviceName());
                smoke.setDeviceStatus(datum.getDeviceStatus());
                smoke.setGmtGreate(new Date(datum.getGmtCreate()));
                smoke.setGmtModified(new Date(datum.getGmtModified()));
                smoke.setNickname(datum.getNickname());
                smoke.setProductKey(datum.getProductKey());
            }
            smkeList.add(smoke);
            page.setList(smkeList);
            page.setTotal(acsResponse.getTotal());
            page.setPageCount(acsResponse.getPageCount());
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return page;
    }

    //根据设备id查询设备状态为火警（4）的数据列表
    @Override
    public PageData<DeviceSmokeEntity> smokePage(Map<String ,Object> param) {
        paramsToLike(param,"title");

        IPage<DeviceSmokeEntity> page = getPage(param, Constant.CREATE_DATE, false);

        List<DeviceSmokeEntity> list = baseDao.List(param);

        return getPageData(list,page.getTotal(), DeviceSmokeEntity.class);
    }
}

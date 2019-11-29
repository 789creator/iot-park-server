package com.szzt.iot.transfer.db.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szzt.iot.common.service.impl.CrudServiceImpl;
import com.szzt.iot.transfer.db.dao.DeviceSmokeAlarmDao;
import com.szzt.iot.transfer.db.dto.DeviceSmokeAlarmDTO;
import com.szzt.iot.transfer.db.entity.DeviceSmokeAlarmEntity;
import com.szzt.iot.transfer.db.service.DeviceSmokeAlarmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 烟雾报警数据
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-11-28
 */
@Service
public class DeviceSmokeAlarmServiceImpl extends CrudServiceImpl<DeviceSmokeAlarmDao, DeviceSmokeAlarmEntity, DeviceSmokeAlarmDTO> implements DeviceSmokeAlarmService {

    @Override
    public QueryWrapper<DeviceSmokeAlarmEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<DeviceSmokeAlarmEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public void saveDB(String content) {
        JSONObject jsonObject = JSONUtil.parseObj(content);
        String deviceType = (String) jsonObject.get("deviceType");
        String iotId = (String) jsonObject.get("iotId");
        String requestId = (String) jsonObject.get("requestId");
        String productKey = (String) jsonObject.get("productKey");
        String deviceName = (String) jsonObject.get("deviceName");
        Long gmtCreate = (Long) jsonObject.get("gmtCreate");
        JSONObject items = (JSONObject) jsonObject.get("items");
        JSONObject itemDeviceType = (JSONObject) items.get("DeviceType");
        JSONObject itemDeviceStatus = (JSONObject) items.get("DeviceStatus");
        JSONObject itemBatteryVoltage = (JSONObject) items.get("BatteryVoltage");

        Long time = (Long) itemBatteryVoltage.get("time");
        DateTime date = DateUtil.date(time);
        DeviceSmokeAlarmEntity deviceSmokeAlarmEntity = new DeviceSmokeAlarmEntity();
        deviceSmokeAlarmEntity.setBatteryVoltage((Double) itemBatteryVoltage.get("value"));
        deviceSmokeAlarmEntity.setDataTime(date);
        deviceSmokeAlarmEntity.setDeviceName(deviceName);
        deviceSmokeAlarmEntity.setDeviceStatus((Integer) itemDeviceStatus.get("DeviceStatus"));
        deviceSmokeAlarmEntity.setCreateDate(new Date());
        this.baseDao.insert(deviceSmokeAlarmEntity);
    }

    public static void main(String[] args) {
        String jsonStr = "{\"deviceType\":\"SmokeAlarm\",\"iotId\":\"g9BmpoRVZdTBdrNnOzJi000100\",\"requestId\":\"12345\",\"productKey\":\"a1tNnA8UI3i\",\"gmtCreate\":1574928531645,\"deviceName\":\"d896e0001c000543\",\"items\":{\"DeviceType\":{\"value\":2,\"time\":1574928531648},\"DeviceStatus\":{\"value\":0,\"time\":1574928531648},\"BatteryVoltage\":{\"value\":9.146,\"time\":1574928531648}}}";
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        String deviceType = (String) jsonObject.get("deviceType");
        String iotId = (String) jsonObject.get("iotId");
        String requestId = (String) jsonObject.get("requestId");
        String productKey = (String) jsonObject.get("productKey");
        String deviceName = (String) jsonObject.get("deviceName");
        Long gmtCreate = (Long) jsonObject.get("gmtCreate");
        JSONObject items = (JSONObject) jsonObject.get("items");
        JSONObject itemDeviceType = (JSONObject) items.get("DeviceType");
        JSONObject itemDeviceStatus = (JSONObject) items.get("DeviceStatus");
        JSONObject itemBatteryVoltage = (JSONObject) items.get("BatteryVoltage");
        System.out.println(items);
        System.out.println(itemDeviceType);
        System.out.println(itemDeviceStatus);
        System.out.println(itemBatteryVoltage);

    }
}
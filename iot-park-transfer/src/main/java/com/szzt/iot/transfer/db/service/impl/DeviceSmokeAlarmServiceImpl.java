package com.szzt.iot.transfer.db.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szzt.iot.common.rabbitmq.MsgHeader;
import com.szzt.iot.common.rabbitmq.MsgHeaderEnum;
import com.szzt.iot.common.rabbitmq.RabbitmqMsg;
import com.szzt.iot.common.rabbitmq.smokealarm.SmokeAlarmMsgBody;
import com.szzt.iot.common.service.impl.CrudServiceImpl;
import com.szzt.iot.transfer.config.TopicSmokeAlarmRabbitConfig;
import com.szzt.iot.transfer.db.dao.DeviceSmokeAlarmDao;
import com.szzt.iot.transfer.db.dto.DeviceSmokeAlarmDTO;
import com.szzt.iot.transfer.db.entity.DeviceSmokeAlarmEntity;
import com.szzt.iot.transfer.db.service.DeviceSmokeAlarmService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private static Logger logger = LoggerFactory.getLogger(DeviceSmokeAlarmServiceImpl.class);
    @Autowired
    RabbitTemplate rabbitTemplate;


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
//        JSONObject smokeSensorState = (JSONObject)items.get("SmokeSensorState");
//        //  发送消息到消息队列
//        this.sendToRabbitmq((Integer) itemDeviceStatus.get("value"));

        Long time = (Long) itemBatteryVoltage.get("time");
        Integer deviceTypeValue = (Integer) itemDeviceType.get("value");
//        Integer smokeSensorStateValue = (Integer) smokeSensorState.get("value");
        DateTime date = DateUtil.date(time);
        DeviceSmokeAlarmEntity deviceSmokeAlarmEntity = new DeviceSmokeAlarmEntity();
        //电压
        deviceSmokeAlarmEntity.setBatteryVoltage((Double) itemBatteryVoltage.get("value"));
        //时间
        deviceSmokeAlarmEntity.setEventTime(date);
        //设备名称
        deviceSmokeAlarmEntity.setDeviceName(deviceName);
        //设备状态
        deviceSmokeAlarmEntity.setDeviceStatus((Integer) itemDeviceStatus.get("value"));
        //设备标识
        deviceSmokeAlarmEntity.setIotId(iotId);
        //设备类型
        deviceSmokeAlarmEntity.setDeviceType(deviceTypeValue);
        //创建时间
        deviceSmokeAlarmEntity.setCreateDate(new Date());
        //产品标识
        deviceSmokeAlarmEntity.setProductKey(productKey);
        //设备类型
        deviceSmokeAlarmEntity.setType(deviceType);
        //烟雾检测状态
//        deviceSmokeAlarmEntity.setSmokeSensorState(smokeSensorStateValue);

        //  发送消息到消息队列
        try {
            this.sendToRabbitmq(deviceSmokeAlarmEntity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("烟雾报警设备发送消息到消息队列出问题了，{}", e.getMessage());
        }

        this.baseDao.insert(deviceSmokeAlarmEntity);
    }

    /**
     * 发送消息到消息队列
     * <p>
     * //     * @param deviceStatus
     */
    private void sendToRabbitmq(DeviceSmokeAlarmEntity deviceSmokeAlarmEntity) throws UnknownHostException {

        // 消息头
        MsgHeader msgHeader = new MsgHeader();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        msgHeader.setSendTime(time);
        msgHeader.setServiceId(MsgHeaderEnum.ServiceIdEnum.SMOKE_ALARM_SERVICE.getCode());
        msgHeader.setCmdId(MsgHeaderEnum.SmokeAlarmMsgCmdIdEnum.SMOKE_ALARM_FIRE.getCode());
        msgHeader.setFromIp(InetAddress.getLocalHost().getHostAddress());

        // 消息体
        SmokeAlarmMsgBody smokeAlarmMsgBody = new SmokeAlarmMsgBody();
        smokeAlarmMsgBody.setDeviceStatus(deviceSmokeAlarmEntity.getDeviceStatus());
        smokeAlarmMsgBody.setDeviceName(deviceSmokeAlarmEntity.getDeviceName());
        smokeAlarmMsgBody.setBatteryVoltage(deviceSmokeAlarmEntity.getBatteryVoltage());
        smokeAlarmMsgBody.setDeviceType(deviceSmokeAlarmEntity.getDeviceType());
        RabbitmqMsg rabbitmqMsg = new RabbitmqMsg<SmokeAlarmMsgBody>();
        rabbitmqMsg.setMsgHeader(msgHeader);
        rabbitmqMsg.setMsgBody(smokeAlarmMsgBody);
        rabbitTemplate.convertAndSend("smokeAlarmTopicExchange", TopicSmokeAlarmRabbitConfig.SMOKE_ALARM_TOPIC, JSONUtil.toJsonStr(rabbitmqMsg));

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
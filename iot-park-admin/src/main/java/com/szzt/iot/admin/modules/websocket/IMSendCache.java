package com.szzt.iot.admin.modules.websocket;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理后端，需要实时显示数据的用户集合
 *
 * @author zhouhongjin
 */
public class IMSendCache {

    public enum CacheType{
        SMOKE_ALARM,TEST
    }

    public final static List<Long> smokeAlarmUserList;

    static {
        smokeAlarmUserList = new ArrayList<Long>();
    }

    /**
     * 添加需要实时显示烟雾报警的用户
     *
     * @param userId
     * @param cacheType
     */
    public static void addIMSendUser(Long userId,CacheType cacheType) {

        switch (cacheType){
            case SMOKE_ALARM:
                smokeAlarmUserList.add(userId);
                break;
        }

    }

    /**
     * 删除需要实时显示烟雾报警的用户
     *
     * @param userId
     * @param cacheType
     */
    public static void deleteIMSendUser(Long userId,CacheType cacheType) {

        switch (cacheType){
            case SMOKE_ALARM:
                smokeAlarmUserList.remove(userId);
                break;
        }

    }


}

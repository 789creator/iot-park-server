package com.szzt.iot.common.rabbitmq;

/**
 * rabbitmq 消息头部枚举
 *
 * @author zhouhongjin
 */

public enum MsgHeaderEnum {
    header_desc("头部描述");
    String desc;

    MsgHeaderEnum(String desc) {
        this.desc = desc;
    }

    public enum ServiceIdEnum {
        //id 区间 00000-49999
        SMOKE_ALARM_SERVICE(10000, "烟雾报警相关操作"),
        SELENIUM_SERVICE(10001, "selenium相关操作"),
        SYSTEM_MONITOR_SERVICE(10002, "系统监控相关操作"),
        HEARTBEAT_SERVICE(20000, "心跳");
        int code;
        String desc;

        ServiceIdEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum SmokeAlarmMsgCmdIdEnum {
        //id 区间 50000-99999
        SMOKE_ALARM_FIRE(50010, "火警");
        int code;
        String desc;

        SmokeAlarmMsgCmdIdEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
    public enum HeartbeatMsgCmdIdEnum {
        TO_CLIENT_HEARTBEAT(60000, "心跳消息给客户端"),
        TO_SERVER_HEARTBEAT(60001, "心跳消息给服务端");
        int code;
        String desc;

        HeartbeatMsgCmdIdEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public enum SystemMonitorCmdIdEnum {
        TO_SERVER_MONITOR_DATA(50020, "执行结果返回服务端");
        int code;
        String desc;

        SystemMonitorCmdIdEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}

package com.szzt.iot.common.rabbitmq;

/**
 * activemq消息头部枚举
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
        SIKULIX_SERVICE(10000, "sikulix相关操作"),
        SELENIUM_SERVICE(10001, "selenium相关操作"),
        SYSTEM_MONITOR_SERVICE(10002, "系统监控相关操作"),
        HEARTBEAT_SERVICE(20000, "心跳"),
        JUDGE_SERVICE(30000, "评委抽取");
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

    public enum SikulixMsgCmdIdEnum {
        //id 区间 50000-99999
        TO_CLIENT_EXE_SCRIPT(50010, "客户端执行脚本指令"),
        TO_SERVER_EXE_RESULT(50020, "执行结果返回服务端"),
        TO_SERVER_EXE_RESULT_DETAIL(50021, "执行结果明细返回服务端"),
        TO_SERVER_EXE_TASK(50030, "文件新增，服务端执行任务分配");
        int code;
        String desc;

        SikulixMsgCmdIdEnum(int code, String desc) {
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

    public enum JudgeMsgCmdIdEnum{
        TO_CLIENT_JUDGE_REPLY(31000, "反馈评委回复结果给机器人脚本");
        int code;
        String desc;
        JudgeMsgCmdIdEnum(int code, String desc) {
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

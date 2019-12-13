package com.szzt.iot.common.websocket;

/**
 * rabbitmq 消息头部枚举
 *
 * @author zhouhongjin
 */

public enum MessageHeaderEnum {
    header_desc("头部描述");
    String desc;

    MessageHeaderEnum(String desc) {
        this.desc = desc;
    }

    public enum ActionCodeEnum {
        GET_SMOKE_ALARM_DATA(1, "获取烟雾报警实时数据");
        int code;
        String desc;

        ActionCodeEnum(int code, String desc) {
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

package com.szzt.iot.common.rabbitmq.sikulix;

/**
 * @author zhouhongjin
 */
public enum SikulixMsgCmdEnum {
    
    TO_CLIENT_EXE_SCRIPT(5010, "客户端执行脚本指令"),
    TO_SERVER_EXE_RESULT(5020, "执行结果返回服务端");

    int code;
    String desc;

    SikulixMsgCmdEnum(int code, String desc) {
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

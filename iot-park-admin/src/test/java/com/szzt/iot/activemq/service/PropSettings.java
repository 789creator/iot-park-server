package com.szzt.iot.activemq.service;

import cn.hutool.setting.Setting;

public class PropSettings {

    public final static Setting setting = new Setting("pro.setting");

    public static void main(String[] args) {
        Setting setting = new Setting("pro.setting");
        String cancel = setting.get("test", "cancel");
        System.out.println(cancel);
    }
}

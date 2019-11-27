package com.szzt.iot.admin.common;

import cn.hutool.setting.Setting;

/**
 * 配置文件加载类
 */
public class PropSettings {


    public final static Setting setting = new Setting("pro.setting");

    public static void main(String[] args) {
        Setting setting = new Setting("pro.setting");
        String cancel = setting.get("scriptBasePath");
        System.out.println(cancel);
    }
}

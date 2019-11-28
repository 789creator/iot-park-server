package com.szzt.iot.amqp;

import cn.hutool.core.codec.Base64;

public class Base64Test {
    public static void main(String[] args) {
        byte[] decode = Base64.decode("01H");
        System.out.println(new String(decode));
    }
}

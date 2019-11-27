package com.szzt.iot.admin.modules.test;

import cn.hutool.core.util.StrUtil;
import com.google.protobuf.ByteString;
import com.szzt.iot.common.netty.DefaultIMHeader;
import com.szzt.iot.common.netty.IMHeader;
import com.szzt.iot.common.netty.IMProtoMessage;
import com.szzt.iot.common.netty.proto.IMBaseDefine;
import com.szzt.iot.common.netty.proto.IMScript;
import com.szzt.iot.admin.modules.netty.server.manager.ClientConnectionInfo;
import com.szzt.iot.admin.modules.netty.server.manager.ClientConnectionMap;
import com.szzt.iot.common.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@RestController
@RequestMapping("/test/netty")
public class NettyTestController {
    @RequestMapping("/send/sikulix")
    public Result sendSikulix(@RequestParam Map<String, Object> params) {
        // 发送Sikulix消息
        IMScript.IMSikulixData.Builder builder = IMScript.IMSikulixData.newBuilder();
        String fromIp = null;
        try {
            fromIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String toIp = (String) params.get("toIp");
        builder.setFromIp(ByteString.copyFromUtf8(fromIp));
        builder.setToIp(ByteString.copyFromUtf8(toIp));
        String scriptType = (String) params.get("scriptType");

        if (StrUtil.isEmpty(scriptType)) {
            builder.setScriptType(ByteString.copyFromUtf8("script-python"));
        } else {
            builder.setScriptType(ByteString.copyFromUtf8(scriptType));
        }
        // 相对路径
        String scriptPath = (String) params.get("scriptPath");
        if (StrUtil.isEmpty(scriptPath)) {
            builder.setScriptFile(ByteString.copyFromUtf8("script/test/script-python.sikuli/test.py"));
        } else {
            builder.setScriptFile(ByteString.copyFromUtf8(scriptPath));
        }

        builder.setCreateTime(System.currentTimeMillis());
        IMHeader header = new DefaultIMHeader(IMBaseDefine.ServiceID.SID_ACTION_VALUE, IMBaseDefine.ActionCmdID.CID_ACTION_EXE_SIKULIX_SCRIPT_VALUE);
        ClientConnectionInfo clientConnectionInfo = ClientConnectionMap.getClientConnectionInfoByClientIp(toIp);
        clientConnectionInfo.getChannelHandlerContext().writeAndFlush(new IMProtoMessage<IMScript.IMSikulixData>(header, builder.build()));
        return new Result();
    }

    @RequestMapping("/send/selenium")
    public Result sendSelenium(@RequestParam Map<String, Object> params) {
        // 发送Sikulix消息
        IMScript.IMSeleniumData.Builder builder = IMScript.IMSeleniumData.newBuilder();
        builder.setFromIp(ByteString.copyFromUtf8("127.0.0.1"));
        builder.setToIp(ByteString.copyFromUtf8("127.0.0.1"));
        String scriptType = (String) params.get("scriptType");
        //type :script-selenium-side、script-selenium-python
        if (StrUtil.isEmpty(scriptType)) {
            builder.setScriptType(ByteString.copyFromUtf8("script-selenium-side"));
        } else {
            builder.setScriptType(ByteString.copyFromUtf8(scriptType));
        }
        // 相对路径
        String scriptPath = (String) params.get("scriptPath");
        if (StrUtil.isEmpty(scriptPath)) {
            builder.setScriptFile(ByteString.copyFromUtf8("script/test/test.side"));
        } else {
            builder.setScriptFile(ByteString.copyFromUtf8(scriptPath));
        }

        builder.setCreateTime(System.currentTimeMillis());
        IMHeader header = new DefaultIMHeader(IMBaseDefine.ServiceID.SID_ACTION_VALUE, IMBaseDefine.ActionCmdID.CID_ACTION_EXE_SELENIUM_SCRIPT_VALUE);
        ClientConnectionInfo clientConnectionInfo = ClientConnectionMap.getClientConnectionInfoByClientIp("127.0.0.1");
        clientConnectionInfo.getChannelHandlerContext().writeAndFlush(new IMProtoMessage<IMScript.IMSeleniumData>(header, builder.build()));
        return new Result();
    }
}

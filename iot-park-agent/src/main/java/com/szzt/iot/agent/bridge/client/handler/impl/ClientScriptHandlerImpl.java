package com.szzt.iot.agent.bridge.client.handler.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.szzt.iot.agent.bridge.PropSettings;
import com.szzt.iot.agent.bridge.client.handler.ClientScriptHandler;
import com.szzt.iot.agent.bridge.client.handler.ScriptExe;
import com.szzt.iot.common.netty.DefaultIMHeader;
import com.szzt.iot.common.netty.IMHeader;
import com.szzt.iot.common.netty.IMProtoMessage;
import com.szzt.iot.common.netty.proto.IMBaseDefine;
import com.szzt.iot.common.netty.proto.IMScript;
import com.szzt.iot.common.netty.vo.AgentScriptVo;
import com.szzt.iot.common.utils.FileDownloadUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

/**
 * 脚本 处理器
 *
 * @author zhouhongjin
 */
@Slf4j
public class ClientScriptHandlerImpl implements ClientScriptHandler {

    public static boolean stopProgram = false;
    public static boolean isRuningScript = false;

    @Override
    public void doSikulixScript(IMHeader header, MessageLite body, ChannelHandlerContext ctx) {
        isRuningScript = true;
        System.out.println("============doSikulixScript============");
        IMScript.IMSikulixData sikulixData = (IMScript.IMSikulixData) body;
        String scriptRelativePath = sikulixData.getScriptFile().toStringUtf8();
//        ClassPathResource classPathResource = new ClassPathResource("sikulix");
//        String classPath = ClientScriptHandlerImpl.class.getResource("/").getPath();
        String scriptBasePath = PropSettings.setting.get("scriptBasePath");
//        String scriptAbsolutePath = classPath + File.separator + "sikulix" + File.separator + scriptRelativePath;
        String scriptAbsolutePath = scriptBasePath + File.separator + scriptRelativePath;
        System.out.println("scriptAbsolutePath=" + scriptAbsolutePath);
        String scriptType = sikulixData.getScriptType().toStringUtf8();
        switch (scriptType) {
            case "script-python":
                ScriptExe.exeScriptPython(scriptAbsolutePath);
                break;
            case "script-sikulix":
                ScriptExe.exeScriptSikulix(scriptAbsolutePath);
                break;
        }
        isRuningScript = false;
        judgeStopProgram();

    }

    @Override
    public void doSeleniumScript(IMHeader header, MessageLite body, ChannelHandlerContext ctx) {
        isRuningScript = true;
        System.out.println("============doSeleniumScript============");
        //todo 改成IMSeleniumData
        IMScript.IMSeleniumData seleniumData = (IMScript.IMSeleniumData) body;
        String scriptRelativePath = seleniumData.getScriptFile().toStringUtf8();
        String scriptBasePath = PropSettings.setting.get("scriptBasePath");
        String scriptAbsolutePath = scriptBasePath + File.separator + scriptRelativePath;
        System.out.println("scriptAbsolutePath=" + scriptAbsolutePath);
        String scriptType = seleniumData.getScriptType().toStringUtf8();
        switch (scriptType) {
            case "script-selenium-python":
                ScriptExe.exeScriptSeleniumPython(scriptAbsolutePath);
                break;
            case "script-selenium-side":
                ScriptExe.exeScriptSeleniumSide(scriptAbsolutePath);
                break;
        }
        isRuningScript = false;
        judgeStopProgram();
    }

    @Override
    public void doSyncScript(IMHeader header, MessageLite body, ChannelHandlerContext ctx) {
        String startTime = DateUtil.now();
        IMScript.IMScriptSync scriptSync = (IMScript.IMScriptSync) body;
        System.out.println("收到服务端发来的消息：" + scriptSync.toString());
        // TODO 下载文件
        JSONArray jsonArray = JSONUtil.parseArray(scriptSync.getScriptData().toStringUtf8());
        List<AgentScriptVo> scriptList = JSONUtil.toList(jsonArray, AgentScriptVo.class);
        String requestPrefix = scriptSync.getRequestPrefix().toStringUtf8();
        String localFilePath = PropSettings.setting.get("scriptBasePath");
        boolean isSuccess = true;
        HashMap<Long, Boolean> downloadResultMap = new HashMap<>();
        for (AgentScriptVo scriptVo : scriptList) {
            try {
                boolean downloadResult = FileDownloadUtil.downloadFileToLocal(requestPrefix + scriptVo.getScriptPath(), localFilePath);
                System.out.println("下载文件是否成功:"+downloadResult);
                downloadResultMap.put(scriptVo.getId(),downloadResult);
            } catch (Exception e) {
                e.printStackTrace();
                isSuccess = false;
                downloadResultMap.put(scriptVo.getId(),false);
                System.out.println("下载文件失败");
            }
        }


        String endTime = DateUtil.now();
        // 反馈下载是否成功状态回服务端
        DefaultIMHeader feedbackIMHeader = new DefaultIMHeader(IMBaseDefine.ServiceID.SID_ACTION_VALUE, IMBaseDefine.ActionCmdID.CID_ACTION_SYNC_SCRIPT_FEEDBACK_VALUE);
        IMScript.IMScriptSyncFeedback.Builder builder = IMScript.IMScriptSyncFeedback.newBuilder();
        builder.setScriptData(ByteString.copyFromUtf8(JSONUtil.toJsonStr(downloadResultMap)));
        builder.setIsSuccess(isSuccess);
        builder.setStartTime(ByteString.copyFromUtf8(startTime));
        builder.setEntTime(ByteString.copyFromUtf8(endTime));
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            builder.setFromIp(ByteString.copyFromUtf8(hostAddress));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            builder.setFromIp(ByteString.copyFromUtf8(""));
        }
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        builder.setToIp(ByteString.copyFromUtf8(inetSocketAddress.getAddress().getHostAddress()));
        ctx.channel().writeAndFlush(new IMProtoMessage<IMScript.IMScriptSyncFeedback>(feedbackIMHeader, builder.build()));
    }


    private void judgeStopProgram() {
        if (stopProgram) {
            System.exit(-1);
        }
    }

}

package com.szzt.iot.admin.modules.netty.server.manager;

import com.google.protobuf.MessageLite;
import com.szzt.iot.admin.modules.netty.server.threadworkers.Server2WebClient;
import com.szzt.iot.admin.modules.netty.server.threadworkers.Server2WebClientData;
import com.szzt.iot.common.netty.IMHeader;
import com.szzt.iot.common.netty.proto.IMBaseDefine;
import com.szzt.iot.admin.modules.netty.server.handler.HeartBeatHandler;
import com.szzt.iot.admin.modules.netty.server.handler.IMTestHandler;
import com.szzt.iot.admin.modules.netty.server.handler.impl.HeartBeatHandlerImpl;
import com.szzt.iot.admin.modules.netty.server.handler.impl.IMTestHandlerImpl;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 处理请求分发
 *
 * @author zhouhongjin
 */
@Slf4j
public class ServerHandlerManager {

    private HeartBeatHandler heartBeatHandler = new HeartBeatHandlerImpl();
    private IMTestHandler iMTestHandler = new IMTestHandlerImpl();


    public void doTest(ChannelHandlerContext ctx, short commandId, IMHeader header, MessageLite body) throws Exception {
        iMTestHandler.test(header, body, ctx);
    }

    /**
     * 服务端接收到客户端的心跳信息处理
     *
     * @param ctx
     * @param commandId
     * @param header
     * @param body
     */
    public void doHeartBeat(ChannelHandlerContext ctx, short commandId, IMHeader header, MessageLite body) {
        heartBeatHandler.doHeartBeat(header,body,ctx);
    }

    /**
     * 处理登录认证
     *
     * @param ctx       信道
     * @param commandId 命令
     * @param header    消息头
     * @param body      消息体
     * @throws Exception
     * @since 1.0
     */
    public void doLogin(ChannelHandlerContext ctx, short commandId, IMHeader header, MessageLite body) throws Exception {
        log.debug("doLogin commandId: {}", commandId);
        switch (commandId) {
            case IMBaseDefine.LoginCmdID.CID_LOGIN_REQ_MSGSERVER_VALUE:
                // this was do at login_server
                log.warn("this was do at login_server: commandId={}", commandId);
                break;
            case IMBaseDefine.LoginCmdID.CID_LOGIN_REQ_USERLOGIN_VALUE:
//                imLoginHandler.login(header, body, ctx);
                break;
            case IMBaseDefine.LoginCmdID.CID_LOGIN_REQ_LOGINOUT_VALUE:
//                imLoginHandler.logOut(header, body, ctx);
                break;
            case IMBaseDefine.LoginCmdID.CID_LOGIN_KICK_USER_VALUE:
//                imLoginHandler.kickUser(header, body, ctx);
                break;
            case IMBaseDefine.LoginCmdID.CID_LOGIN_REQ_DEVICETOKEN_VALUE:
//                imLoginHandler.deviceToken(header, body, ctx);
                break;
            case IMBaseDefine.LoginCmdID.CID_LOGIN_REQ_KICKPCCLIENT_VALUE:
//                imLoginHandler.kickPcClient(header, body, ctx);
                break;
            case IMBaseDefine.LoginCmdID.CID_LOGIN_REQ_PUSH_SHIELD_VALUE:
//                imLoginHandler.pushShield(header, body, ctx);
                break;
            case IMBaseDefine.LoginCmdID.CID_LOGIN_REQ_QUERY_PUSH_SHIELD_VALUE:
//                imLoginHandler.queryPushShield(header, body, ctx);
                break;
            default:
                log.warn("Unsupport command id {}", commandId);
                break;
        }
    }


    /**
     * 处理消息相关消息类型
     *
     * @param ctx       信道
     * @param commandId 命令
     * @param header    消息头
     * @param body      消息体
     * @since 1.0
     */
    public void doMessage(ChannelHandlerContext ctx, short commandId, IMHeader header, MessageLite body) {

        // 判断是否登录
//        if (!hasLogin(ctx)) {
//            return ;
//        }
//
        log.debug("doMessage commandId: {}", commandId);
        log.debug("doMessage body: {}", body.toString());
        switch (commandId) {
            case IMBaseDefine.MessageCmdID.CID_MSG_DATA_VALUE:
                // 消息发送
//                imMessageHandler.sendMessage(header, body, ctx);
                break;
            case IMBaseDefine.MessageCmdID.CID_MSG_DATA_ACK_VALUE:
//                imMessageHandler.clientMsgDataAck(header, body, ctx); //不需要在这里处理，可以删除
                break;
            case IMBaseDefine.MessageCmdID.CID_MSG_READ_ACK_VALUE:
//                imMessageHandler.readMessage(header, body, ctx);
                break;
            case IMBaseDefine.MessageCmdID.CID_MSG_TIME_REQUEST_VALUE:
//            	imMessageHandler.clientTimeReq(header, body, ctx);
                break;
            case IMBaseDefine.MessageCmdID.CID_MSG_UNREAD_CNT_REQUEST_VALUE:
//                imMessageHandler.getUnreadCount(header, body, ctx);
                break;
            case IMBaseDefine.MessageCmdID.CID_MSG_LIST_REQUEST_VALUE:
//                imMessageHandler.getMessageList(header, body, ctx);
                break;
            case IMBaseDefine.MessageCmdID.CID_MSG_GET_LATEST_MSG_ID_REQ_VALUE:
//                imMessageHandler.getLatestMessageId(header, body, ctx);
                break;
            case IMBaseDefine.MessageCmdID.CID_MSG_GET_BY_MSG_ID_REQ_VALUE://
//                imMessageHandler.getByMessageId(header, body, ctx);
                break;
            default:
                log.warn("Unsupport command id {}", commandId);
                break;
        }
    }

    /**
     * @param ctx
     * @since 1.0
     */
    public void remove(ChannelHandlerContext ctx) {
        //todo

    }

    /**
     * 掉线时的处理
     *
     * @param ctx
     * @since 1.0
     */
    public void offline(ChannelHandlerContext ctx) {
        //todo
    }


    /**
     * 连接时的处理
     *
     * @param ctx
     * @since 1.0
     */
    public void online(ChannelHandlerContext ctx) {
        //todo
    }

    /**
     * 保存客户端连接
     *
     * @param ctx
     */
    public void add(ChannelHandlerContext ctx) {

        ClientConnectionMap.addClientConnection(ctx);
    }


    public void doAction(ChannelHandlerContext ctx, short commandId, IMHeader header, MessageLite body) {
        switch (commandId) {
            case IMBaseDefine.ActionCmdID.CID_ACTION_SYNC_SCRIPT_FEEDBACK_VALUE:
                Server2WebClient.up2WebClient.add(new Server2WebClientData());
                log.info("收到反馈消息 {}", body.toString());
                break;

            default:
                log.warn("Unsupport command id {}", commandId);
                break;
        }
    }
}

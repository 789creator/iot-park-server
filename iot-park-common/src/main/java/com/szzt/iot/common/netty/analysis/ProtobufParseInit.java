package com.szzt.iot.common.netty.analysis;

import com.szzt.iot.common.netty.proto.IMBaseDefine;
import com.szzt.iot.common.netty.proto.IMHeartBeat;
import com.szzt.iot.common.netty.proto.IMMessage;
import com.szzt.iot.common.netty.proto.IMScript;

/**
 * ProtobufParseMap 初始化
 *
 * @author zhouhongjin
 */
public class ProtobufParseInit {

    public static void init() {
        // 心跳CmdID
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_HEART_BEAT_VALUE, IMBaseDefine.HeartBeat.CID_HEART_BEAT_DEFAULT_VALUE,
                IMHeartBeat.IMHeartBeatData::parseFrom, IMHeartBeat.IMHeartBeatData.class);

        // MessageCmdID
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_MSG_VALUE, IMBaseDefine.MessageCmdID.CID_MSG_DATA_VALUE,
                IMMessage.IMMsgData::parseFrom, IMMessage.IMMsgData.class);
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_MSG_VALUE, IMBaseDefine.MessageCmdID.CID_MSG_DATA_ACK_VALUE,
                IMMessage.IMMsgDataAck::parseFrom, IMMessage.IMMsgDataReadAck.class);
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_MSG_VALUE, IMBaseDefine.MessageCmdID.CID_MSG_READ_ACK_VALUE,
                IMMessage.IMMsgDataReadAck::parseFrom, IMMessage.IMMsgDataReadAck.class);
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_MSG_VALUE, IMBaseDefine.MessageCmdID.CID_MSG_READ_NOTIFY_VALUE,
                IMMessage.IMMsgDataReadNotify::parseFrom, IMMessage.IMMsgDataReadNotify.class);
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_MSG_VALUE, IMBaseDefine.MessageCmdID.CID_MSG_TIME_REQUEST_VALUE,
                IMMessage.IMClientTimeReq::parseFrom, IMMessage.IMClientTimeReq.class);
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_MSG_VALUE, IMBaseDefine.MessageCmdID.CID_MSG_UNREAD_CNT_REQUEST_VALUE,
                IMMessage.IMUnreadMsgCntReq::parseFrom, IMMessage.IMUnreadMsgCntReq.class);
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_MSG_VALUE, IMBaseDefine.MessageCmdID.CID_MSG_LIST_REQUEST_VALUE,
                IMMessage.IMGetMsgListReq::parseFrom, IMMessage.IMGetMsgListReq.class);
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_MSG_VALUE, IMBaseDefine.MessageCmdID.CID_MSG_GET_LATEST_MSG_ID_REQ_VALUE,
                IMMessage.IMGetLatestMsgIdReq::parseFrom, IMMessage.IMGetLatestMsgIdReq.class);
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_MSG_VALUE, IMBaseDefine.MessageCmdID.CID_MSG_GET_BY_MSG_ID_REQ_VALUE,
                IMMessage.IMGetMsgByIdReq::parseFrom, IMMessage.IMGetMsgByIdReq.class);

        // script CmdID
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_ACTION_VALUE, IMBaseDefine.ActionCmdID.CID_ACTION_EXE_SIKULIX_SCRIPT_VALUE,
                IMScript.IMSikulixData::parseFrom, IMScript.IMSikulixData.class);
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_ACTION_VALUE, IMBaseDefine.ActionCmdID.CID_ACTION_EXE_SELENIUM_SCRIPT_VALUE,
                IMScript.IMSeleniumData::parseFrom, IMScript.IMSeleniumData.class);
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_ACTION_VALUE, IMBaseDefine.ActionCmdID.CID_ACTION_SYNC_SCRIPT_VALUE,
                IMScript.IMScriptSync::parseFrom, IMScript.IMScriptSync.class);
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_ACTION_VALUE, IMBaseDefine.ActionCmdID.CID_ACTION_SYNC_SCRIPT_FEEDBACK_VALUE,
                IMScript.IMScriptSyncFeedback::parseFrom, IMScript.IMScriptSyncFeedback.class);
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_ACTION_VALUE, IMBaseDefine.ActionCmdID.CID_ACTION_SYNC_FILE_VALUE,
                IMScript.IMFileSync::parseFrom, IMScript.IMFileSync.class);
        ProtobufParseMap.register(IMBaseDefine.ServiceID.SID_ACTION_VALUE, IMBaseDefine.ActionCmdID.CID_ACTION_SYNC_FILE_FEEDBACK_VALUE,
                IMScript.IMFileSyncFeedback::parseFrom, IMScript.IMFileSyncFeedback.class);
    }
}

package com.szzt.iot.common.netty;

import com.szzt.iot.common.constant.SysConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouhongjin
 */
public class DefaultIMHeader extends IMHeader {
    
    private Logger logger = LoggerFactory.getLogger(DefaultIMHeader.class);
    
    public DefaultIMHeader(int commandId) {
        setVersion((short) SysConstant.PROTOCOL_VERSION);
        setFlag((short) SysConstant.PROTOCOL_FLAG);
        setServiceId(commandId >> 8);
        setCommandId((short)commandId);
        short seqNo = (short)0;
        setSeqnum(seqNo);
        setReserved((short)SysConstant.PROTOCOL_RESERVED);

        logger.trace("packet#construct Default Header -> serviceId:{}, commandId:{}, seqNo:{}", commandId, seqNo);
    }
    
    public DefaultIMHeader(int serviceId, int commandId) {
        setVersion((short) SysConstant.PROTOCOL_VERSION);
        setFlag((short) SysConstant.PROTOCOL_FLAG);
        setServiceId((short)serviceId);
        setCommandId((short)commandId);
        short seqNo = (short)0; //?? FIXME
        setSeqnum(seqNo);
        setReserved((short)SysConstant.PROTOCOL_RESERVED);

        logger.trace("packet#construct Default Header -> serviceId:{}, commandId:{}, seqNo:{}", serviceId, commandId, seqNo);
    }
}
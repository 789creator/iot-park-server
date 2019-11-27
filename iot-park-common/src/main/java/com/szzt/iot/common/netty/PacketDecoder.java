package com.szzt.iot.common.netty;

import com.google.protobuf.MessageLite;
import com.szzt.iot.common.constant.SysConstant;
import com.szzt.iot.common.netty.analysis.ProtobufParseMap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 接收消息处理(Protobuf to IMProtoMessage)
 *
 * @author zhouhongjin
 */
@Slf4j
public final class PacketDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(final ChannelHandlerContext ctx, final ByteBuf in, final List<Object> out) throws Exception {
        try {

            log.trace("Protobuf decode started.");
            in.markReaderIndex();
            if (in.readableBytes() < 4) {
                log.debug("Readable Bytes length less than 4 bytes, ignored");
                in.resetReaderIndex();
                return;
            }

            DataBuffer dataBuf = new DataBuffer(in);

            IMHeader header = new IMHeader();
            header.decode(dataBuf);

            if (header.getLength() < 0) {
                ctx.close();
                log.error("message length less than 0, channel closed");
                return;
            }

            ByteBuf byteBuf = ctx.alloc().buffer(header.getLength() - SysConstant.PROTOCOL_HEADER_LENGTH);

            in.readBytes(byteBuf);
            byte[] body;
            if (byteBuf.hasArray()) {
                body = byteBuf.array();
            } else {
                body = new byte[byteBuf.capacity()];
                byteBuf.readBytes(body);
            }

            MessageLite msg = ProtobufParseMap.getMessage(header.getServiceId(), header.getCommandId(), body);

            IMProtoMessage<MessageLite> protoMessage = new IMProtoMessage<>(header, msg);
            out.add(protoMessage);

            log.trace("Received protobuf : length={}, commandId={}", header.getLength(), header.getCommandId());
        } catch (Exception e) {
            log.error(ctx.channel().remoteAddress() + ",decode failed.", e);
        } finally {
            log.trace("Protobuf decode finished.");
        }
    }

}

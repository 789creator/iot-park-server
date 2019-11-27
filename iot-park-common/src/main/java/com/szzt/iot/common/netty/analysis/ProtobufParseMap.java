package com.szzt.iot.common.netty.analysis;

import com.google.protobuf.MessageLite;
import com.szzt.iot.common.netty.proto.IMBaseDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理消息解析的协议版本
 * 
 */
public final class ProtobufParseMap {

    private static Logger logger = LoggerFactory.getLogger(ProtobufParseMap.class);

    @FunctionalInterface
    public interface Parsing {
        MessageLite process(byte[] bytes) throws IOException;
    }

    /** Protobuf parses Map */
    public static Map<Integer, Map<Integer, Parsing>> parseServiceMap =
            new HashMap<>();

    static {

        // Initialize the parses map
        IMBaseDefine.ServiceID[] serviceIDs = IMBaseDefine.ServiceID.values();
        for (IMBaseDefine.ServiceID serviceId : serviceIDs) {
            parseServiceMap.put(serviceId.getNumber(),
                    new HashMap<Integer, Parsing>());
}
    }

    /**
     * Forbid for instancing
     */
    private ProtobufParseMap() {
    }

    /**
     * Register a parse for the protobuf.
     *
     * @param serviceId the service id
     * @param commandId the command id
     * @param parse the protobuf parse
     * @since  1.0
     */
    public static void register(final int serviceId, final int commandId, final ProtobufParseMap.Parsing parse) {
        Map<Integer, Parsing> parserMap = parseServiceMap.get(serviceId);
        if (parserMap == null) {
            logger.error("Protobuf service has not been registered in parseMap, serviceId: {}",
                    serviceId);
            return;
        }

        ProtobufParseMap.Parsing parser = parserMap.get(commandId);
        if (parser == null) {
            parserMap.put(commandId, parse);
        } else {
            logger.warn("Protobuf command has been registered in parseMap： serviceId={}, commandId={}",
                    serviceId, commandId);
            return;
        }
    }

    /**
     * Register a parse for the protobuf.
     *
     * @param serviceId the service id
     * @param commandId the command id
     * @param parse the protobuf parse
     * @param claz the claz is prepared for reverse parsing(message to serviceId and commandId)
     * @since  1.0
     */
    public static void register(final int serviceId, final int commandId, final ProtobufParseMap.Parsing parse, Class<?> claz) {
        register(serviceId, commandId, parse);

        // FIXME
        // do some thing for the reverse parsing
    }
    /**
     * Convert a protobuf to message object according to the serviceId and commandId.
     *
     * @param serviceId the service id
     * @param commandId the command id
     * @param bytes the protobuf to be parsed
     * @return the parsed message object
     * @throws IOException
     * @since  1.0
     */
    public static MessageLite getMessage(final int serviceId, final int commandId, final byte[] bytes)
            throws IOException {
        Map<Integer, Parsing> parserMap = parseServiceMap.get(serviceId);
        if (parserMap == null) {
            throw new IOException("UnKnown Protocol service: " + serviceId);
        }

        ProtobufParseMap.Parsing parser = parserMap.get(commandId);
        if (parser == null) {
            throw new IOException(
                    "UnKnown Protocol commandId: service=" + serviceId + ",command=" + commandId);
        }

        MessageLite msg = parser.process(bytes);
        return msg;
    }

}

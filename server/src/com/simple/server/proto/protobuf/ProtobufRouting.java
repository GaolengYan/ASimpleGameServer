package com.simple.server.proto.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.simple.server.proto.protobuf.auto.LoginMessage;

import java.util.HashMap;

/**
 * 将protobuf根据协议号转化成对应的messagelite
 **/
public class ProtobufRouting {
    private HashMap<Integer, Parser> routMap = new HashMap<>();
    private static ProtobufRouting protobufRouting = new ProtobufRouting();

    private ProtobufRouting(){
        init();
    }

    public MessageLite routing(int requestId, byte[] body, int offset, int length){
        try {
            return (MessageLite) routMap.get(requestId).parseFrom(body, offset, length);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void init(){
        routMap.put(10001, LoginMessage.LoginRequest.getDefaultInstance().getParserForType());
    }

    public static ProtobufRouting getInstance() {
        return protobufRouting;
    }


}

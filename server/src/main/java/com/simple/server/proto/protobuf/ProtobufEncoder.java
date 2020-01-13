package com.simple.server.proto.protobuf;

import com.google.protobuf.MessageLite;
import com.simple.server.proto.Request;
import com.simple.server.proto.binary.Protocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/** Protobuf编码器，提供增加协议头的功能
/* 协议头 协议体的长度 + 协议号
/* --------------------------
/* |  length:4  |   cmd:4   |
/* -------------------------- */
public class ProtobufEncoder extends MessageToByteEncoder<Request> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Request request, ByteBuf byteBuf) throws Exception {
        int cmd = request.getCmd();
        MessageLite messageLite = request.getMessageLite();

        byte[] body = messageLite.toByteArray();
        byte[] header = packHeader(cmd, body.length);

        byte[] result = Protocol.byteMerger(header, body);
        byteBuf.writeBytes(result);

    }

    // 打包消息头
    private byte[] packHeader(int cmd, int bodyLength){
        byte[] header = new byte[8];
        header[3] = (byte) (bodyLength & 0xff);
        header[2] = (byte) ((bodyLength >> 8) & 0xff);
        header[1] = (byte) ((bodyLength >> 16) & 0xff);
        header[0] = (byte) ((bodyLength >> 24) & 0xff);
        // 协议号
        header[7] = (byte) (cmd & 0xff);
        header[6] = (byte) ((cmd >> 8) & 0xff);
        header[5] = (byte) ((cmd >> 16) & 0xff);
        header[4] = (byte) ((cmd >> 24) & 0xff);
        return header;
    }

}

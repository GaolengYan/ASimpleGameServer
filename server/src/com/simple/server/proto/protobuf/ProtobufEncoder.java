package com.simple.server.proto.protobuf;

import com.google.protobuf.MessageLite;
import com.simple.server.proto.ProtoMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/** Protobuf编码器，提供增加协议头的功能
/* 协议头 协议体的长度 + 协议号
/* --------------------------
/* |  length:4  |   cmd:4   |
/* -------------------------- */
public class ProtobufEncoder extends MessageToByteEncoder<ProtoMsg> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ProtoMsg protoMsg, ByteBuf byteBuf) throws Exception {
        int cmd = protoMsg.getCmd();
        MessageLite messageLite = protoMsg.getMessageLite();

        byte[] body = messageLite.toByteArray();
        byte[] header = packHeader(cmd, body.length);

        byte[] result = pack(header, body);
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
    // 拼接消息头和消息体
    private byte[] pack(byte[] header, byte[] body){
        byte[] result = new byte[header.length + body.length];
        System.arraycopy(header, 0, result, 0, header.length);
        System.arraycopy(body, 0, result, header.length, body.length);
        return result;
    }
}

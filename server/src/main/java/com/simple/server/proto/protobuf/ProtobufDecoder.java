package com.simple.server.proto.protobuf;

import com.google.protobuf.MessageLite;
import com.simple.server.proto.Request;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

// Protobuf协议解码器
public class ProtobufDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 8) {
            return;
        }
        byteBuf.markReaderIndex();
        int bodyLength = byteBuf.readInt();           // 消息体长度
        int requestId = byteBuf.readInt();            // 协议号

        if (byteBuf.readableBytes() < bodyLength) { // 读取可读字节长度
            byteBuf.resetReaderIndex();
            return;
        }
        ByteBuf commandBytes = byteBuf.readBytes(bodyLength);
        byte[] array;
        int offset;
        if (commandBytes.hasArray()) {
            array = commandBytes.array();
            offset = commandBytes.arrayOffset() + commandBytes.readerIndex();
        } else {
            array = ByteBufUtil.getBytes(commandBytes, commandBytes.readerIndex(), bodyLength, false);
            offset = 0;
        }
        MessageLite msg = decodeBody(requestId, array, offset, bodyLength);
        ReferenceCountUtil.release(commandBytes);
        list.add(new Request(requestId, msg, channelHandlerContext.channel()));
    }

    private MessageLite decodeBody(int requestId, byte[] body, int offset, int length) {
        return ProtobufRouting.getInstance().routing(requestId, body, offset, length);
    }
}

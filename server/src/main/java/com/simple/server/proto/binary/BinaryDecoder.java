package com.simple.server.proto.binary;

import com.simple.server.proto.Request;
import com.simple.server.proto.binary.auto.Pt;
import com.simple.server.proto.binary.auto.Pt100;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.HashMap;
import java.util.List;

public class BinaryDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 8) {
            return;
        }
        byteBuf.markReaderIndex();
        short bodyLength = byteBuf.readShortLE();           // 消息体长度
        int cmd = byteBuf.readShortLE();            // 协议号

        System.out.println("bodyLength:" + bodyLength);
        System.out.println("requestId:" + cmd);

        byte[] body = new byte[bodyLength];
        byteBuf.readBytes(body);
        Pt pt = BinaryRouting.routing(cmd);
        if (pt != null){
            HashMap argsMap = pt.decode(cmd, body);
            Request request = new Request(cmd, argsMap);
            list.add(request);
        }
    }
}

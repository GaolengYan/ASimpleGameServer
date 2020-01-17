package com.simple.server.proto.binary;

import com.simple.server.proto.Response;
import com.simple.server.proto.binary.auto.Pt;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.Arrays;


public class BinaryEncoder extends MessageToByteEncoder<Response> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Response response, ByteBuf byteBuf) throws Exception {
        int cmd = response.getCmd();
        Pt pt = BinaryRouting.routing(cmd);
        System.out.println(response.toString());
        if (pt != null){
            byte[] body = pt.encode(cmd, response.getHashMap());
            byte[] cmdBytes = Protocol.writeInt16(response.getCmd());
            byte[] length = Protocol.writeInt16(body.length);
            byte[] result = Protocol.byteMergerAll(length, cmdBytes, body);
            byteBuf.writeBytes(result);
        }
    }
}

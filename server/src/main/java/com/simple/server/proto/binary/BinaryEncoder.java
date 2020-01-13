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
        System.out.println("cmd" + cmd);
        Pt pt = BinaryRouting.routing(cmd);
        System.out.println(response.toString());
        if (pt != null){
            System.out.println("1111111111");
            byte[] body = pt.encode(cmd, response.getHashMap());
            System.out.println("body" + Arrays.toString(body));
            byte[] cmdBytes = Protocol.writeInt16(response.getCmd());
            System.out.println("cmdBytes" + Arrays.toString(cmdBytes));
            byte[] length = Protocol.writeInt16(body.length);
            System.out.println("length" + Arrays.toString(length));
            byte[] result = Protocol.byteMergerAll(length, cmdBytes, body);
            System.out.println("result" + Arrays.toString(result));
            byteBuf.writeBytes(result);
        }
    }
}

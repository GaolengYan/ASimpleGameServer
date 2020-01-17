package com.simple.server.proto.binary;

import com.simple.server.proto.Request;
import com.simple.server.proto.binary.auto.Pt;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.HashMap;
import java.util.List;

public class BinaryDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 8) {  // 因为协议头为length:int16+cmd:int16共32位8个字节
            return;                         // 因此当可读数据小于8位时我们不处理
        }
        short bodyLength = byteBuf.readShortLE();   // 消息体长度
        int cmd = byteBuf.readShortLE();            // 协议号
        byte[] body = new byte[bodyLength];         //
        byteBuf.readBytes(body);                    // 读出消息体
        Pt pt = BinaryRouting.routing(cmd);         // 根据协议号分发消息体到对应的协议解析类
        if (pt != null){
            HashMap argsMap = pt.decode(cmd, body); // 解析得到参数map
            Request request = new Request(cmd, argsMap, channelHandlerContext.channel()); // 打包成Request对象
            list.add(request);                      // 将解析好的数据放入结果
        }
    }
}

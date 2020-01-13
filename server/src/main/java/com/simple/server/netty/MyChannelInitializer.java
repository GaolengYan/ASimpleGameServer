package com.simple.server.netty;

import com.simple.server.proto.binary.BinaryDecoder;
import com.simple.server.proto.binary.BinaryEncoder;
import com.simple.server.proto.protobuf.ProtobufDecoder;
import com.simple.server.proto.protobuf.ProtobufEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * 自定义channel注册流程
 */
public class MyChannelInitializer extends ChannelInitializer {
    private String ptType;

    public MyChannelInitializer(String ptType){
        this.ptType = ptType;
    }

    @Override
    protected void initChannel(Channel channel) {
        System.out.println("客户端连接:" + channel.remoteAddress());
        ChannelPipeline pipeline = channel.pipeline();
        if (ptType.equals("protobuf")){
            pipeline.addLast(new ProtobufDecoder());
            pipeline.addLast(new ProtobufEncoder());
        }else{
            pipeline.addLast(new BinaryDecoder());
            pipeline.addLast(new BinaryEncoder());
        }
        pipeline.addLast(new SocketServerHandler());
    }
}

package com.simple.server.netty;

import com.simple.server.proto.protobuf.ProtobufDecoder;
import com.simple.server.proto.protobuf.ProtobufEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * 自定义channel注册流程
 */
public class MyChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) {
        System.out.println("客户端连接:" + channel.remoteAddress());
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new ProtobufDecoder());
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new SocketServerHandler());
    }
}

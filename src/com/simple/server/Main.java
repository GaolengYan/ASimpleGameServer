package com.simple.server;


import com.simple.server.netty.MyChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Main {
    private static final String IP = "127.0.0.1";   // 写死IP
    private static final int PORT = 8088;           // 写死绑定的端口

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();    // boss对象，用于监听socket连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();  // worker对象，用于数据读写与处理逻辑
        ServerBootstrap bootstrap = new ServerBootstrap();  // 引导类，引导服务器的启动
        bootstrap
                .group(bossGroup, workerGroup)          // 双线程绑定
                .channel(NioServerSocketChannel.class)  // 指定IO模型为NIO
                .childHandler(new MyChannelInitializer())
                .bind(IP, PORT).sync();
        System.out.println("服务器启动！");
    }
}
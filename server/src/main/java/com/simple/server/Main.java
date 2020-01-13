package com.simple.server;


import com.simple.server.login.action.LoginAction;
import com.simple.server.netty.MyChannelInitializer;
import com.simple.server.proto.Request;
import com.simple.server.proto.binary.Protocol;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.javatuples.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class Main {
    private static Properties properties = new Properties();

    public static void main(String[] args) throws Exception {
        loadProperties(properties); // 载入配置
        start();    // 启动服务器
//        test();
    }

    private static void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();    // boss对象，用于监听socket连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();  // worker对象，用于数据读写与处理逻辑
        ServerBootstrap bootstrap = new ServerBootstrap();  // 引导类，引导服务器的启动
        bootstrap
                .group(bossGroup, workerGroup)          // 双线程绑定
                .channel(NioServerSocketChannel.class)  // 指定IO模型为NIO
                .childHandler(new MyChannelInitializer(properties.getProperty("pt_type")))
                .bind(properties.getProperty("ip"), Integer.parseInt(properties.getProperty("port"))).sync();
        System.out.println("服务器启动！");
    }

    // 加载配置
    private static void loadProperties(Properties p){
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("Config.properties");
        try {
            p.load(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
//    协议测试方法
    private static void test(){
//        String s = "测试";
//        byte[] bytes = Protocol.writeString(s);
//        Pair pair = Protocol.readString(bytes);
//        System.out.println(pair.getValue0());
//        HashMap hashMap = new HashMap();
//        hashMap.put(1, "1234");
//        hashMap.put(2, "1234");
//        Request request = new Request(10001, hashMap);
//        LoginAction loginAction = new LoginAction();
//        loginAction.action(10001, request);
    }
}
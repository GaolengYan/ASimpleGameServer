package com.simple.server;

import com.simple.server.auto.Protocol;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            InetAddress addr;
            Socket socket = new Socket("127.0.0.1", 9999);
            addr = socket.getInetAddress();
            InputStream inputStream = socket.getInputStream();
            OutputStream outPutStream = socket.getOutputStream();
            System.out.println("连接到" + addr);

//            String lineString = "连接测试";
            LoginMessage.LoginRequest.Builder builder = LoginMessage.LoginRequest.newBuilder();
            String accName = "garry";
            String passWord = "123456";
            byte[] accNameBytes = Protocol.writeString(accName);
            byte[] passWordBytes = Protocol.writeString(passWord);
            byte[] body = Protocol.byteMerger(accNameBytes, passWordBytes);
            int bodyLength = body.length;
            int requestId = 10001;
            byte[] length = Protocol.writeInt16(bodyLength);
            byte[] cmd = Protocol.writeInt16(requestId);
            byte[] result = Protocol.byteMergerAll(length, cmd, body);
            outPutStream.write(result);
        } catch (IOException e) {
            System.out.println("无法连接");
        }
    }
}

package com.simple.server;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            InetAddress addr;
            Socket socket = new Socket("127.0.0.1", 8088);
            addr = socket.getInetAddress();
            InputStream inputStream = socket.getInputStream();
            OutputStream outPutStream = socket.getOutputStream();
            System.out.println("连接到" + addr);

//            String lineString = "连接测试";
            LoginMessage.LoginRequest.Builder builder = LoginMessage.LoginRequest.newBuilder();

            builder.setAccName("garry");
            builder.setPassWord("12345");
            LoginMessage.LoginRequest request = builder.build();
            byte[] body = request.toByteArray();
            byte[] header = new byte[8];
            // 长度
            int bodyLength = body.length;

            header[3] = (byte) (bodyLength & 0xff);
            header[2] = (byte) ((bodyLength >> 8) & 0xff);
            header[1] = (byte) ((bodyLength >> 16) & 0xff);
            header[0] = (byte) ((bodyLength >> 24) & 0xff);
            // 协议号
            int requestId = 10001;
            header[7] = (byte) (requestId & 0xff);
            header[6] = (byte) ((requestId >> 8) & 0xff);
            header[5] = (byte) ((requestId >> 16) & 0xff);
            header[4] = (byte) ((requestId >> 24) & 0xff);

            byte[] result = new byte[header.length + body.length];

            System.arraycopy(header, 0, result, 0, header.length);
            System.arraycopy(body, 0, result, header.length, body.length);

            outPutStream.write(result);

            DataInputStream dataInputStream = new DataInputStream(inputStream);
            bodyLength =  dataInputStream.readInt();
            requestId =  dataInputStream.readInt();
            System.out.println("服务器返回:" + bodyLength);
            System.out.println("服务器返回:" + requestId);

        } catch (IOException e) {
            System.out.println("无法连接");
        }
    }
}

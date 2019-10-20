package com.simple.server.action;

import com.google.protobuf.MessageLite;
import com.simple.server.proto.protobuf.auto.LoginMessage;
import com.simple.server.session.SessionManager;

/**
 * @Author：yanweifan
 * @E-mail；a349162727@qq.com
 * @CreateDate：2019/10/20
 * @Description:
 */
public class LoginAction implements Action {
    @Override
    public void action(int cmd, MessageLite messageLite) {
        if (messageLite instanceof LoginMessage.LoginRequest){
            LoginMessage.LoginRequest request = (LoginMessage.LoginRequest) messageLite;
            System.out.println("客户端发来：" + request.getAccName());
            System.out.println("客户端发来：" + request.getPassWord());
            SessionManager.getSessionManager().send2All(messageLite);
        }
    }
}

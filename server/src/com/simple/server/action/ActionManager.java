package com.simple.server.action;

import com.google.protobuf.MessageLite;
import com.simple.server.proto.ProtoMsg;

import java.util.HashMap;

/**
 * @Author：yanweifan
 * @E-mail；a349162727@qq.com
 * @CreateDate：2019/10/20
 * @Description: 协议转发模块
 */
public class ActionManager {
    private HashMap<Integer, Action> actionMap = new HashMap<>();

    private void init(){
        actionMap.put(10001, new LoginAction());
    }

    public void dispatch(ProtoMsg protoMsg) {
        int cmd = protoMsg.getCmd();
        MessageLite messageLite = protoMsg.getMessageLite();
        Action action = actionMap.get(cmd);
        if(action != null) {
            action.action(cmd, messageLite);
        }

    }



    public ActionManager(){
        init();
    }

}

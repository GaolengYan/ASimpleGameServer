package com.simple.server.action;

import com.simple.server.login.action.LoginAction;
import com.simple.server.proto.Request;

import java.util.HashMap;

/**
 * @author yanweifan
 * @date 2019/10/20
 * @E-mail a349162727@qq.com
 * @Description  协议转发模块
 */
public class ActionManager {
    private HashMap<Integer, Action> actionMap = new HashMap<>();

    private void init(){

    }

    public void dispatch(Request request) {
        int cmd = request.getCmd();
        Action action = actionMap.get(cmd);
        if(action != null) {
            action.action(cmd, request);
        }
    }



    public ActionManager(){
        init();
    }

}

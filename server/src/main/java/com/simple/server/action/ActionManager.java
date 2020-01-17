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
    private static ActionManager actionManager = new ActionManager();
    private HashMap<Integer, Action> actionMap = new HashMap<>();

    private void init(){
        actionMap.put(100, (Action) new LoginAction());
    }

    public void dispatch(Request request) {
        int cmd = request.getCmd();
        Action action = actionMap.get(cmd / 100);
        if(action != null) {
            action.action(cmd, request);
        }
    }

    private ActionManager(){
        init();
    }

    public static ActionManager getInstance() {
        return actionManager;
    }
}

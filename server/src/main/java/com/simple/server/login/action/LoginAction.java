package com.simple.server.login.action;

import com.simple.server.action.Action;
import com.simple.server.login.dao.Player;
import com.simple.server.proto.Request;
import com.simple.server.proto.Response;
import com.simple.server.util.HibernateUtil;
import io.netty.channel.Channel;
import org.hibernate.Session;

import java.util.HashMap;

/**
 * @Author：yanweifan
 * @E-mail；a349162727@qq.com
 * @CreateDate：2019/10/20
 * @Description:
 */
public class LoginAction implements Action {
    public void action(int cmd, Request request) {
        Channel channel = request.getChannel();
        HashMap argsMap = request.getHashMap();
        String accName = (String) argsMap.get(1);
        String passWord = (String) argsMap.get(2);
        HashMap responseArgsMap = new HashMap();
        int res;
        Session session = HibernateUtil.getSession();
        Player playerEntity = session.get(Player.class, accName);
        // 判断用户名密码对不对
        if (playerEntity == null){
            res = 0;
            System.out.println("帐号未注册！");
        } else {
            String truePassWord = playerEntity.getPassWord();
            if (passWord.equals(truePassWord)) {
                res = 1;
                System.out.println("登录成功！");
            } else {
                res = 2;
                System.out.println("密码有误！");
            }
        }
        responseArgsMap.put(1, res);
        Response response = new Response(cmd, responseArgsMap);
        channel.writeAndFlush(response);
    }
}

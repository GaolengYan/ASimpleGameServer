package com.simple.server.session;

import io.netty.channel.Channel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class SessionManager {
    private static SessionManager sessionManager = new SessionManager();
    private HashMap<String, Session> sessionMap = new HashMap<>();

    private SessionManager() {}

    public static SessionManager getSessionManager(){
        return sessionManager;
    }

    public void addSession(Channel ch){
        Session session = new Session();
        session.setChannel(ch);
        String SessionId = createSessionId();
        session.setSessionId(SessionId);
        sessionMap.put(SessionId, session);
    }

    public Session getSession(String sessionId){
        if (sessionMap.containsKey(sessionId)){
            return sessionMap.get(sessionId);
        }else {
            return new Session();
        }
    }

    private void removeSession(String sessionId){
        sessionMap.remove(sessionId);
    }

    // 随机获取SessionId，当前时间+随机五位数
    private String createSessionId(){
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
        return rannum + str;// 当前时间
    }

    public void send2All(Object object){
        for (Session session: sessionMap.values()) {
            session.getChannel().writeAndFlush(object);
        }
    }
}

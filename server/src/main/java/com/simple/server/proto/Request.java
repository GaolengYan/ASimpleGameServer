package com.simple.server.proto;

import com.google.protobuf.MessageLite;

import java.util.HashMap;

public class Request {
    private int cmd;
    private MessageLite messageLite;
    private HashMap hashMap;

    public Request(int cmd, MessageLite messageLite) {
        this.cmd = cmd;
        this.messageLite = messageLite;
    }

    public Request(int cmd, HashMap hashMap){
        this.cmd = cmd;
        this.hashMap = hashMap;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public MessageLite getMessageLite() {
        return messageLite;
    }

    public void setMessageLite(MessageLite messageLite) {
        this.messageLite = messageLite;
    }

    public HashMap getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap hashMap) {
        this.hashMap = hashMap;
    }
}

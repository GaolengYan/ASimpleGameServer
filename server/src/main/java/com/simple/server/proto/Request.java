package com.simple.server.proto;

import com.google.protobuf.MessageLite;
import io.netty.channel.Channel;

import java.util.HashMap;

public class Request {
    private int cmd;
    private MessageLite messageLite;
    private HashMap hashMap;
    private Channel channel;

    public Request(int cmd, MessageLite messageLite, Channel channel) {
        this.cmd = cmd;
        this.messageLite = messageLite;
        this.channel = channel;
    }

    public Request(int cmd, HashMap hashMap, Channel channel){
        this.cmd = cmd;
        this.hashMap = hashMap;
        this.channel = channel;
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

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}

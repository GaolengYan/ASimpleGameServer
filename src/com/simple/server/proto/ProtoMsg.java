package com.simple.server.proto;

import com.google.protobuf.MessageLite;

public class ProtoMsg {
    private int cmd;
    private MessageLite messageLite;

    public ProtoMsg(int cmd, MessageLite messageLite) {
        this.cmd = cmd;
        this.messageLite = messageLite;
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
}

package com.simple.server.proto;

import java.util.HashMap;

public class Response {
    private int cmd;
    private HashMap hashMap;

    public Response(int cmd, HashMap hashMap) {
        this.cmd = cmd;
        this.hashMap = hashMap;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public HashMap getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public String toString() {
        return "Response{" +
                "cmd=" + cmd +
                ", hashMap=" + hashMap +
                '}';
    }
}

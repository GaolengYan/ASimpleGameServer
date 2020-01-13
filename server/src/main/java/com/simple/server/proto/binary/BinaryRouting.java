package com.simple.server.proto.binary;

import com.simple.server.proto.binary.auto.Pt;
import com.simple.server.proto.binary.auto.Pt100;

import java.util.HashMap;

public class BinaryRouting {

    private static HashMap hashMap = new HashMap<Short, Object>();
    private static BinaryRouting binaryRouting = new BinaryRouting();

    private BinaryRouting(){
        init();
    }

    private void init() {
        hashMap.put(100, new Pt100());
    }

    public static Pt routing(int cmd){
        return (Pt) hashMap.get(cmd / 100);
    }

}

package com.simple.server.proto.binary.auto;

import java.util.HashMap;

public interface Pt {
    HashMap decode(int cmd, byte[] bytes);
    byte[] encode(int cmd, HashMap hashMap);
}

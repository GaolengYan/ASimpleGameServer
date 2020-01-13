package com.simple.server.proto.binary.auto;

import com.simple.server.proto.binary.Protocol;
import org.javatuples.Pair;

import java.util.HashMap;

public class Pt100 implements Pt {

	public HashMap decode(int cmd, byte[] bytes){
		HashMap hashMap = new HashMap();
		if (cmd == 10001){
			Pair args1 = Protocol.readString(bytes);
			String accName = (String) args1.getValue0();
			byte[] leftBytes = (byte[]) args1.getValue1();
			Pair args2 = Protocol.readString(leftBytes);
			String passWord = (String) args2.getValue0();
			hashMap.put(1, accName);
			hashMap.put(2, passWord);
		}
		return hashMap;
	}

	public byte[] encode(int cmd, HashMap hashMap){
        System.out.println("cmd" + cmd);

        if (cmd == 10001){
			int res = (int) hashMap.get(1);
			System.out.println("res" + res);
			return Protocol.writeInt32(res);
		}
		return new byte[0];
	}
}

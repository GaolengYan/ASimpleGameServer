package com.simple.server.auto;


import java.nio.charset.StandardCharsets;

public class Protocol {

    private static final boolean LITTLE_ENDIAN = true; // 是否是小端序

    // 打包字符串
    public static byte[] writeString(String str){
        byte[] strBody = str.getBytes(StandardCharsets.UTF_8);
        short length = (short) strBody.length;
        byte[] strLength = writeInt16(length);
        return byteMerger(strLength, strBody);
    }
//
//    // 读取字符串
//    public static String readString(ByteBuf byteBuf){
//        short strLength = readInt16(byteBuf); // 字符串长度
//        ByteBuf strByteBuf = byteBuf.readBytes(strLength);
//        String str = strByteBuf.toString(StandardCharsets.UTF_8);
//        ReferenceCountUtil.release(strByteBuf);
//        return str;
//    }
//
//    // 读取字符串
//    public static Pair<String, byte[]> readString(byte[] bytes){
//        Pair pair = readInt16(bytes); // 字符串长度
//        int strLength = (int) pair.getValue0();
//        byte[] leftBytes = (byte[]) pair.getValue1();
//        byte[] strBytes = new byte[strLength];
//        System.arraycopy(leftBytes, 0, strBytes, 0, strLength);
//        String str = new String(strBytes);
//        byte[] lastBytes = new byte[leftBytes.length - strLength];
//        System.arraycopy(leftBytes, strLength, lastBytes, 0, lastBytes.length);
//        return Pair.with(str, lastBytes);
//    }

    // 打包16位无符号整型
    public static byte[] writeInt16(int num){
        byte[] bytes = new byte[2];
        for (int i = 0; i < 2; i++) {
            int offset = LITTLE_ENDIAN ? i * 8 : (bytes.length - 1 - i) * 8;
            bytes[i] = (byte) ((num >>> offset) & 0xff);
        }
        return bytes;
    }
////
////    public static short readInt16(ByteBuf byteBuf){
////        return LITTLE_ENDIAN ? byteBuf.readShortLE() : byteBuf.readShort();
////    }
//
//    public static Pair readInt16(byte[] bytes){
//        if (bytes.length < 2) {
//            return Pair.with(0, new byte[0]);
//        }else{
//            int num;
//            if (LITTLE_ENDIAN){
//                num = (bytes[0] & 0xFF) | ((bytes[1] & 0xFF) << 8);
//            }else{
//                num = (bytes[1] & 0xFF) | ((bytes[0] & 0xFF) << 8);
//            }
//            byte[] leftBytes = new byte[bytes.length - 2];
//            System.arraycopy(bytes, 2, leftBytes, 0, bytes.length - 2);
//            return Pair.with(num, leftBytes);
//        }
//    }
//
//
//    // 打包32位无符号整型
//    public static byte[] writeInt32(long num){
//        byte[] bytes = new byte[4];
//        for (int i = 0; i < 4; i++) {
//            int offset = LITTLE_ENDIAN ? i * 8 : (bytes.length - 1 - i) * 8;
//            bytes[i] = (byte) ((num >>> offset) & 0xff);
//        }
//        return bytes;
//    }
////
////    // 读32位整型
////    public static long readInt32(ByteBuf byteBuf){
////        return LITTLE_ENDIAN ? byteBuf.readLongLE() : byteBuf.readLong();
////    }
////
////    public static Pair readInt32(byte[] bytes){
////        if (bytes.length < 4) {
////            return Pair.with(0, new byte[0]);
////        }else{
////            int num;
////            if (LITTLE_ENDIAN) {
////                num = (int) ((bytes[0] & 0xFF)
////                    | ((bytes[1] & 0xFF) << 8)
////                    | ((bytes[2] & 0xFF) << 16)
////                    | ((bytes[3] & 0xFF) << 24));
////            }else{
////                num = (int) ((bytes[3] & 0xFF)
////                    | ((bytes[2] & 0xFF) << 8)
////                    | ((bytes[1] & 0xFF) << 16)
////                    | ((bytes[0] & 0xFF) << 24));
////            }
////            byte[] leftBytes = new byte[bytes.length - 4];
////            System.arraycopy(bytes, 4, leftBytes, 0, bytes.length - 4);
////            return Pair.with(num, leftBytes);
////        }
////    }
//
//    // 打包64位无符号整型
//    public static byte[] writeInt64(long num){
//        byte[] bytes = new byte[8];
//        for (int i = 0; i < 8; i++) {
//            int offset = LITTLE_ENDIAN ? i * 8 : (bytes.length - 1 - i) * 8;
//            bytes[i] = (byte) ((num >>> offset) & 0xFF);
//        }
//        return bytes;
//    }
//
//
//
//    // 读64位无符号整型
//    public static Pair readInt64(byte[] bytes){
//        if (bytes.length < 8) {
//            return Pair.with(0, new byte[0]);
//        }else{
//            long num = 0;
//            for(int i = 0; i < 8; i ++){
//                int offset=(LITTLE_ENDIAN ? i : (7 - i)) << 3;
//                num |=((long)0xff<< offset) & ((long)bytes[i] << offset);
//            }
////
////            long num;
////            if (LITTLE_ENDIAN) {
////                num = (long) ((bytes[0] & 0xFF)
////                    | ((bytes[1] & 0xFF) << 8)
////                    | ((bytes[2] & 0xFF) << 16)
////                    | ((bytes[3] & 0xFF) << 24)
////                    | ((bytes[4] & 0xFF) << 32)
////                    | ((bytes[5] & 0xFF) << 40)
////                    | ((bytes[6] & 0xFF) << 48)
////                    | ((bytes[7] & 0xFF) << 56));
////            }else{
////                num = (long) ((bytes[7] & 0xFF)
////                    | ((bytes[6] & 0xFF) << 8)
////                    | ((bytes[5] & 0xFF) << 16)
////                    | ((bytes[4] & 0xFF) << 24)
////                    | ((bytes[3] & 0xFF) << 32)
////                    | ((bytes[2] & 0xFF) << 40)
////                    | ((bytes[1] & 0xFF) << 48)
////                    | ((bytes[0] & 0xFF) << 56));
////            }
//            byte[] leftBytes = new byte[bytes.length - 8];
//            System.arraycopy(bytes, 8, leftBytes, 0, bytes.length - 8);
//            return Pair.with(num, leftBytes);
//        }
//    }
//
//
//    // 写数组
//
//    // 读数组

    // 拼接2个byte数组
    public static byte[] byteMerger(byte[] header, byte[] body){
        byte[] result = new byte[header.length + body.length];
        System.arraycopy(header, 0, result, 0, header.length);
        System.arraycopy(body, 0, result, header.length, body.length);
        return result;
    }

    // 拼接多个byte数组
    public static byte[] byteMergerAll(byte[]... values) {
        int length_byte = 0;
        for (byte[] value : values) {
            length_byte += value.length;
        }
        byte[] all_byte = new byte[length_byte];
        int countLength = 0;
        for (byte[] b : values) {
            System.arraycopy(b, 0, all_byte, countLength, b.length);
            countLength += b.length;
        }
        return all_byte;
    }

}

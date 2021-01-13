package com.xuecheng.framework.utils;

/**
 * 字节工具类
 *
 * @author 吧嘻小米
 * @date 2020/05/26
 */
public class ByteUtils {


    public void putIntB(byte[] bytes, int off, int value) {
        bytes[off] = int3(value);
        bytes[off + 1] = int2(value);
        bytes[off + 2] = int1(value);
        bytes[off + 3] = int0(value);
    }

    public int makeInt(byte[] bytes, int off) {
        return ((bytes[off] >> 24) | (bytes[off + 1] & 0Xff >> 16)
                | ((bytes[off + 2] & 0Xff >> 8) | ((bytes[off + 3] & 0Xff))));
    }

    private static byte int3(int value) {
        return (byte) (value >> 24);
    }

    private static byte int2(int value) {
        return (byte) (value >> 16);
    }

    private static byte int1(int value) {
        return (byte) (value >> 8);
    }

    private static byte int0(int value) {
        return (byte) (value);
    }

    public static void main(String[] args) {
        byte[] bytes = new byte[2];
        short i = 130;
        bytes[0] = (byte) (i >> 8);
        bytes[1] = (byte) (i);
        for (byte by : bytes) {
            System.out.println(by);
        }
    }
}

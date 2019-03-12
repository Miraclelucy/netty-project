package com.tenglu.ch00.niodemo;


import java.nio.ByteBuffer;

/**
 *  @author lushiqin 20190311
 * 《Netty源码解析-张龙》第37讲 java nio 深入详解buffer中的方法putInt(),putLong(),putChar(),putShort(),putDouble()
 *
 * */
public class BufferMethodTest
{
    public static void main(String[] args) {
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        byteBuffer.putChar('a');
        byteBuffer.putShort((short)12);
        byteBuffer.putInt(30);
        byteBuffer.putLong(500000000L);
        byteBuffer.putDouble(13.123456);

        byteBuffer.flip();
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getDouble());

    }

}

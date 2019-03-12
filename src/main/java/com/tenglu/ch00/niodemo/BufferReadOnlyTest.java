package com.tenglu.ch00.niodemo;

import java.nio.ByteBuffer;

/**
 *  @author lushiqin 20190311
 * 《Netty源码解析-张龙》第37讲 java nio 深入详解buffer-只读buffer
 *
 * */
public class BufferReadOnlyTest {

    public static void main(String[] args) {

        ByteBuffer byteBuffer=ByteBuffer.allocate(10);
        System.out.println(byteBuffer.getClass());
        for(int i=0;i<10;++i){
            byteBuffer.put((byte)i);//1 这里特别需要注意的是：要转换为byte类型再放进去
        }
        ByteBuffer onlyReadBuffer=byteBuffer.asReadOnlyBuffer();

        System.out.println(onlyReadBuffer.getClass());



    }
}

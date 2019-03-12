package com.tenglu.ch00.niodemo;


import java.nio.ByteBuffer;

/**
 *  @author lushiqin 20190311
 * 《Netty源码解析-张龙》第37讲 java nio 深入详解buffer中的方法slice()
 *
 * */
public class BufferSliceTest
{
    public static void main(String[] args) {
        ByteBuffer byteBuffer=ByteBuffer.allocate(10);
        for(int i=0;i<10;++i){
            byteBuffer.put((byte)i);//1 这里特别需要注意的是：要转换为byte类型再放进去
        }

        byteBuffer.position(2);
        byteBuffer.limit(6);

        ByteBuffer slicebuffer=byteBuffer.slice();


        for(int i=0;i<slicebuffer.capacity();i++){
            int b=slicebuffer.get(i); //2 新的buffer读取时指定位置
            b=b*2;
            slicebuffer.put(i,(byte)b);//3 写入时指定位置
        }

        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        for(int i=0;i<byteBuffer.capacity();i++){
            System.out.println(byteBuffer.get());
        }
        System.out.println();


    }

}

package com.tenglu.ch00.niodemo;


import java.nio.IntBuffer;
import java.util.Random;

/**
 *  @author lushiqin 20190311
 * 《Netty源码解析-张龙》第36讲 java nio 深入详解buffer中的属性:capacity,limit,position
 *
 * */
public class BufferFlipTest {

    public static void main(String[] args) {
        IntBuffer intBuffer=IntBuffer.allocate(10);
        for(int i=0;i<10;i++){
            int j= new Random().nextInt(10);
            intBuffer.put(j);
        }
        System.out.println("limit:"+intBuffer.limit());
        System.out.println("position:"+intBuffer.position());
        System.out.println("capacity:"+intBuffer.capacity());
        intBuffer.flip();

        while(intBuffer.hasRemaining()){
            int b=intBuffer.get();
            System.out.println(b);
            System.out.println("limit:"+intBuffer.limit());
            System.out.println("position:"+intBuffer.position());
            System.out.println("capacity:"+intBuffer.capacity());
        }


    }
}

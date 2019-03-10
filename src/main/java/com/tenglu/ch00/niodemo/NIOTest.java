package com.tenglu.ch00.niodemo;


import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *  @author lushiqin 20190310
 * 《Netty源码解析-张龙》第33讲 java nio 深入详解之buffer和channel
 *
 * */
public class NIOTest {

    public static void main(String[] args) throws  Exception{
        //打印当前项目的path
        System.out.println(System.getProperty("user.dir"));

        //1 往ByteBuffer中写数据
        FileInputStream fileInputStream=new FileInputStream("1.txt");
        FileChannel fileChannel=fileInputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(225);
        fileChannel.read(byteBuffer);

        //2 翻转Buffer
        byteBuffer.flip();

        //3 读取ByteBuffer
        while(byteBuffer.hasRemaining()){
            byte b=byteBuffer.get();
            System.out.println((char)b);

        }
        fileInputStream.close();

    }

}

package com.tenglu.ch00.niodemo;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *  @author lushiqin 20190311
 * 《Netty源码解析-张龙》第38讲 java nio 深入详解buffer-DirectByteBuffer
 *
 * */
public class BufferDirectTest {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream=new FileInputStream("input2.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("output.txt");
        FileChannel fileintChannel=fileInputStream.getChannel();
        FileChannel fileoutChannel=fileOutputStream.getChannel();

        //1 创建一个直接缓冲
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(1024);

        while(true){
            byteBuffer.clear();

           int read=fileintChannel.read(byteBuffer);
           System.out.println("read:"+read);

           if(-1==read){
               break;
           }
           byteBuffer.flip();

           fileoutChannel.write(byteBuffer);

       }
        fileintChannel.close();
        fileoutChannel.close();
        fileInputStream.close();
        fileOutputStream.close();



    }
}

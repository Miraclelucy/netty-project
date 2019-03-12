package com.tenglu.ch00.niodemo;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

/**
 *  @author lushiqin 20190311
 * 《Netty源码解析-张龙》第35讲 java nio 深入详解buffer中的方法clear()
 *
 * */
public class BufferClearTest {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream=new FileInputStream("input.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("output.txt");
        FileChannel fileintChannel=fileInputStream.getChannel();
        FileChannel fileoutChannel=fileOutputStream.getChannel();

        //1 往buffer中写数据
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        while(true){
            byteBuffer.clear();//如果把这一行注释掉，会出现什么情况呢？要思考一下

           int read=fileintChannel.read(byteBuffer);//什么情况下会出现read=0和read=-1呢？要思考一下
           System.out.println(read);

           if(-1==read){
               break;
           }
           //2 翻转bufer
           byteBuffer.flip();

           //3 buffer中的数据读取至文件
           fileoutChannel.write(byteBuffer);

       }
        fileintChannel.close();
        fileoutChannel.close();
        fileInputStream.close();
        fileOutputStream.close();



    }
}

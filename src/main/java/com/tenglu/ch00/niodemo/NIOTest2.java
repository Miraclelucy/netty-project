package com.tenglu.ch00.niodemo;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *  @author lushiqin 20190310
 * 《Netty源码解析-张龙》第33讲 java nio 深入详解之buffer和channel
 *
 * */
public class NIOTest2 {

    public static void main(String[] args) throws  Exception{
        //1 创建文件输出流
        FileOutputStream fileOutputStream=new FileOutputStream("2.txt");
        FileChannel fileChannel=fileOutputStream.getChannel();

        //2 创建Buffer,
        ByteBuffer byteBuffer=ByteBuffer.allocate(521);
        byte[] messages="life is short,learn python".getBytes();
        for (int i=0;i<messages.length;i++){
            byteBuffer.put(messages[i]);
        }

        //3 翻转Buffer
        byteBuffer.flip();

        //4 将Buffer读取至channel中
        fileChannel.write(byteBuffer);

        fileOutputStream.close();



    }

}

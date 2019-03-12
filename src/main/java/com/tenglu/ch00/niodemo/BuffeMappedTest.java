package com.tenglu.ch00.niodemo;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 *  @author lushiqin 20190311
 * 《Netty源码解析-张龙》第38讲 java nio 深入详解buffer-DirectByteBuffer
 *
 * */
public class BuffeMappedTest {

    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile=new RandomAccessFile("input3.txt","rw");
        FileChannel fileChannel=randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_WRITE,0,5);
        mappedByteBuffer.put(0,(byte)'a');
        mappedByteBuffer.put(3,(byte)'b');




    }
}

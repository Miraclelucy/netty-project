package com.tenglu.ch00.niodemo;


import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 *  @author lushiqin 20190312
 * 《Netty源码解析-张龙》第38讲 java nio 深入详解buffer- Scattering(分散)与Gathering（聚集
 *
 * */
public class BuffeScatteringTest {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8899));

        SocketChannel socketChannel=serverSocketChannel.accept();

        ByteBuffer[] byteBuffers=new   ByteBuffer[3];
        long messagelength=9;

        byteBuffers[0]=ByteBuffer.allocate(2);
        byteBuffers[1]=ByteBuffer.allocate(3);
        byteBuffers[2]=ByteBuffer.allocate(4);

        while(true){
            long byteread=0;
            while(byteread<messagelength){
                //1 将所有数据写入buffer
                byteread+=socketChannel.read(byteBuffers);
                System.out.println("byteread:"+byteread);

                //2 打印当前buffer的各属性值
                Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                    System.out.println("buffer positon:"+byteBuffer.position()+"  buffer limit:"+byteBuffer.limit()+"  buffer capacity:"+byteBuffer.capacity());
                });
            }

            //3 翻转所有的buffer
            Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                byteBuffer.flip();
            });

            //4 将buffer中的数据回写至服务器端
            socketChannel.write(byteBuffers);

            //3 翻转所有的buffer
            Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                byteBuffer.clear();
            });

        }


    }
}

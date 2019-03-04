package com.tenglu.ch00.niodemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  @author lushiqin 20190127
 * 《Netty快速入门教程-第一课NIO》传统的阻塞式IO
 *
 * */
public class OldIOServer {

    public static void main(String[] args) throws Exception {
        //1 创建ServerSocket,监听10101端口
        ServerSocket server=new ServerSocket(10101);
        System.out.println("服务端启动！");

        //2 创建Socket
        /*Socket socket=server.accept();
        System.out.println("来了一个新的客户端！");*/

        //2 线程池的方式创建Socket
        ExecutorService executorService= Executors.newCachedThreadPool();
        while (true){
            final Socket socket=server.accept();
            System.out.println("来了一个新的客户端！");
            executorService.execute(new Runnable() {
                public void run() {
                    //3 处理Socket
                    handle(socket);
                }
            });

        }



    }

    public static void handle( Socket socket){
        try {
            //1 从Socket中获取字节流
            InputStream inputStream=socket.getInputStream();
            //2 读取字节流
            byte[] bytes=new byte[1024];
            while(true){
                int read =inputStream.read(bytes);
                if(read!=-1){
                    String str=new String(bytes,0,read);
                    System.out.print(str);
                }

            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            //3 关闭Socket
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

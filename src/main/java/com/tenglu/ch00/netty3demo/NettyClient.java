package com.tenglu.ch00.netty3demo;


import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  @author lushiqin 20190128
 * 《Netty快速入门教程-第三课 Netty客户端》
 *
 * */
public class NettyClient {
    public static void main(String[] args) {
        //1 创建客户端
        ClientBootstrap client=new ClientBootstrap();

        //2 设置NIOSocket工厂
        ExecutorService boss= Executors.newCachedThreadPool();
        ExecutorService worker= Executors.newCachedThreadPool();

        client.setFactory(new NioClientSocketChannelFactory(boss,worker));

        //3 设置通道工厂
        client.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline channel= Channels.pipeline();
                channel.addLast("decoder",new StringDecoder());
                channel.addLast("encoder",new StringEncoder());
                channel.addLast("hihandler",new HiHandler());
                return channel;
            }
        });

        //4 连接端口
        ChannelFuture connect=client.connect(new InetSocketAddress("127.0.0.1",10101));


        System.out.println("客户端启动成功");
        Channel channel=connect.getChannel();

        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("请输入：");
            channel.write(scanner.next());
        }


    }

}

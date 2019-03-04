package com.tenglu.ch00.netty3demo;


import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  @author lushiqin 20190128
 * 《Netty快速入门教程-第二课 Netty服务端》
 *
 * */
public class NettyServer {


    public static void main(String[] args) {
        //1 创建服务端
        final ServerBootstrap server= new ServerBootstrap();

        //2 设置NIOSocket工厂
        ExecutorService boss= Executors.newCachedThreadPool();
        ExecutorService worker= Executors.newCachedThreadPool();

        server.setFactory(new NioServerSocketChannelFactory(boss,worker));


        //3 设置通道工厂
        server.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline channel= Channels.pipeline();
                channel.addLast("decoder",new StringDecoder());
                channel.addLast("encoder",new StringEncoder());
                channel.addLast("hellohandler",new HelloHandler());
                return channel;
            }
        });

        //4 绑定端口
        server.bind(new InetSocketAddress(10101));


        System.out.println("启动服务端");

    }


}

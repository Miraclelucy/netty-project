package com.tenglu.ch04;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 *  @author lushiqin 20190224
 * 《Netty源码解析-张龙》第9讲 心跳检测
 *
 * */
public class MyServer {

    public static void main(String[] args) throws InterruptedException{
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();

        try{
            //1 增加了handler的设置，设置为LoggingHandler；
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(boss,worker)
                            .channel(NioServerSocketChannel.class)
                            .handler(new LoggingHandler(LogLevel.INFO))
                            .childHandler(new ChannelInitializer<SocketChannel>() {

                                @Override
                                protected  void initChannel(SocketChannel socketChannel){
                                    ChannelPipeline ch=socketChannel.pipeline();
                                    //2 增加了空闲检测的
                                    ch.addLast(new IdleStateHandler(5,7,3, TimeUnit.SECONDS));
                                    ch.addLast(new MyServerHandler());
                                }
                            });

            //3 绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            channelFuture.channel().closeFuture().sync();

        }finally {
                boss.shutdownGracefully();
                worker.shutdownGracefully();
        }

    }

}

package com.tenglu.ch01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 *  @author lushiqin 20190220
 * 《Netty源码解析-张龙》第5-6讲 分析channel的执行流程和基本组件
 *
 * */

public class TestServer {

    public static void main(String[] args) throws InterruptedException {

        //1 申明两个线程池
        EventLoopGroup boss =new NioEventLoopGroup();
        EventLoopGroup worker =new NioEventLoopGroup();

        try {
            //2 设置服务端启动类参数
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(boss,worker)
                            .channel(NioServerSocketChannel.class)
                            .childHandler(new TestServerInitializer());

            //3 绑定端口
            ChannelFuture channelFuture=serverBootstrap.bind(10101).sync();
            channelFuture.channel().closeFuture().sync();
        }
        finally {
            //4 优雅关闭线程池
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }

}

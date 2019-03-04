package com.tenglu.ch03;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 *  @author lushiqin 20190221
 * 《Netty源码解析-张龙》第8讲 多客户端连接
 *
 * */
public class MyChatServer {
    public static void main(String[] args) throws Exception {

        // 1 申明2个线程池
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        // 2 设置服务端启动类
        try {
            //1  handler和childHandler 的区别 ：
            // handler对应boss ；childHandler对应worker
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
                            pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new MyChatServerHandler());

                        }

                    });
            // 3 绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            //channelFuture.channel().writeAndFlush("服务端启动");
            channelFuture.channel().closeFuture().sync();


        } finally {

            boss.shutdownGracefully();
            worker.shutdownGracefully();

        }
    }
}

package com.tenglu.ch05;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;



/**
 *  @author lushiqin 20190224
 * 《Netty源码解析-张龙》第10-11讲 基于websocket的服务器 实现长连接
 *
 * */
public class MyWebSocketServer {

    public static void main(String[] args) throws InterruptedException{
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected  void initChannel(SocketChannel socketChannel){
                            ChannelPipeline ch=socketChannel.pipeline();
                            ch.addLast(new HttpServerCodec());
                            ch.addLast(new ChunkedWriteHandler());//1 以块的方式来写的处理器
                            ch.addLast(new HttpObjectAggregator(8192));//2 对Http消息聚合，聚合到一个FullHttpRequest或者FullHttpResponse
                            ch.addLast(new WebSocketServerProtocolHandler("/ws")); //3 协议的名字 ws://server:port/conext_path
                            ch.addLast(new MyWebSocketServerHandler()); //4 自己写的handler处理类
                        }
                    });

            //4 绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            channelFuture.channel().closeFuture().sync();

        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}

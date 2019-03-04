package com.tenglu.ch07;


import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 *  @author lushiqin 20190226
 * 《Netty源码解析-张龙》第15讲 Protobuf与netty集成
 *
 * */
public class ProtobufClient {

    public static void main(String[] args) throws Exception{
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();

        try{
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel){
                            ChannelPipeline ch=socketChannel.pipeline();
                            ch.addLast(new ProtobufVarint32FrameDecoder());//1
                            ch.addLast(new ProtobufVarint32LengthFieldPrepender());//2
                            ch.addLast(new ProtobufDecoder(PersonInfo.Person.getDefaultInstance()));//3
                            ch.addLast(new ProtobufEncoder());//4

                            ch.addLast(new ProtobufClientHandler());


                        }
                    });

            ChannelFuture channelFuture=bootstrap.connect("localhost",8899).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            eventLoopGroup.shutdownGracefully();
        }

    }

}

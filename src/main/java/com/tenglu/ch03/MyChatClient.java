package com.tenglu.ch03;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *  @author lushiqin 20190221
 * 《Netty源码解析-张龙》第8讲 多客户端连接
 *
 * */
public class MyChatClient {

    public static void main(String[] args)  throws Exception{
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        try{
            //1 设置启动类
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel){
                            ChannelPipeline ch=socketChannel.pipeline();
                            ch.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
                            ch.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            ch.addLast(new StringEncoder(CharsetUtil.UTF_8));
                            ch.addLast(new MyChatClientHandler());
                        }
                    });
            //2 获取channel
            Channel channel=bootstrap.connect("localhost",8899).sync().channel();

            //3 读取用户在控制台的输入
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
            for(;;){
                channel.writeAndFlush(bufferedReader.readLine()+"\r\n");
            }

        }finally {
            //4 优雅关闭
            eventLoopGroup.shutdownGracefully();
        }

    }
}

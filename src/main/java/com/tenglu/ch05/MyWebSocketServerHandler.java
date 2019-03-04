package com.tenglu.ch05;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 *  @author lushiqin 20190224
 * 《Netty源码解析-张龙》第10-11讲 基于websocket的服务器 实现长连接
 *
 * */
public class MyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("服务器收到消息"+msg.text());

        //1 向通道写数据时，必须采用TextWebSocketFrame对象
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间："+ LocalDateTime.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println( LocalDateTime.now()+"---handlerAdded---"+ctx.channel().id().asLongText());
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println( LocalDateTime.now()+"---handlerRemoved---"+ctx.channel().id().asLongText());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生");
        ctx.close();
    }


}

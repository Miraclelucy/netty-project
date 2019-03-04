package com.tenglu.ch02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 *  @author lushiqin 20190221
 * 《Netty源码解析-张龙》第7讲 基于netty的简单客户端和服务端
 *
 * */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    //服务端向客服端发送消息时，会触发该方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"  "+msg);

        //返回服务端写数据
        ctx.channel().writeAndFlush("from client :" + UUID.randomUUID());

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        cause.printStackTrace();
        super.exceptionCaught(ctx,cause);
    }


}


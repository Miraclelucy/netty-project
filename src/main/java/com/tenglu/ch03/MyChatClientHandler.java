package com.tenglu.ch03;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 *  @author lushiqin 20190221
 * 《Netty源码解析-张龙》第8讲 多客户端连接
 *
 * */
public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //Channel channel=ctx.channel();
        //channel.writeAndFlush();
        System.out.println(msg);

    }



}
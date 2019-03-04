package com.tenglu.ch07;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *  @author lushiqin 20190226
 * 《Netty源码解析-张龙》第15讲 Protobuf与netty集成
 *
 * */
public class ProtobufClientHandler extends SimpleChannelInboundHandler<PersonInfo.Person>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,PersonInfo.Person person) throws  Exception{
        System.out.println(person.getEmail());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)throws Exception{
        PersonInfo.Person person= PersonInfo.Person.newBuilder()
                .setId(2).setEmail("zhangsan222@163.com").setName("张三222").build();
        ctx.channel().writeAndFlush(person);

    }

}

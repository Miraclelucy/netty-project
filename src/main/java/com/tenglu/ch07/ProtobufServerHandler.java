package com.tenglu.ch07;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *  @author lushiqin 20190226
 * 《Netty源码解析-张龙》第15讲 Protobuf与netty集成
 *
 * */
public class ProtobufServerHandler extends SimpleChannelInboundHandler<PersonInfo.Person>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,PersonInfo.Person person) throws  Exception{
        System.out.println(person.getEmail());
        System.out.println(person.getName());
        System.out.println(person.getId());


    }



}

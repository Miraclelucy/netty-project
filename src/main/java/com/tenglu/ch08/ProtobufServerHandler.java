package com.tenglu.ch08;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *  @author lushiqin 20190226
 * 《Netty源码解析-张龙》第16讲 Protobuf与netty集成 在socket上传输多种类型的protobuf数据
 * *  参考帖子 https://blog.csdn.net/hami700100332/article/details/80457548
 * */
public class ProtobufServerHandler extends SimpleChannelInboundHandler<DataInfo.MyMessage>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,DataInfo.MyMessage msg) throws  Exception{
       if(msg.getDataType()==DataInfo.MyMessage.DataType.PeopleType){
           DataInfo.People people=msg.getPeople();
           System.out.println("people---"+people.getEmail());
           System.out.println("people---"+people.getName());
           System.out.println("people---"+people.getId());
       }
        else if(msg.getDataType()==DataInfo.MyMessage.DataType.CatType){
           DataInfo.Cat cat=msg.getCat();
           System.out.println("cat---"+cat.getCity());
           System.out.println("cat---"+cat.getName());
       }
       else if(msg.getDataType()==DataInfo.MyMessage.DataType.DogType){
           DataInfo.Dog dog=msg.getDog();
           System.out.println("dog---"+dog.getAge());
           System.out.println("dog---"+dog.getName());
       }

    }

}

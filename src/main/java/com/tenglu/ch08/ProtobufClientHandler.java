package com.tenglu.ch08;


import com.tenglu.ch07.PersonInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 *  @author lushiqin 20190303
 * 《Netty源码解析-张龙》第16讲 Protobuf与netty集成 在socket上传输多种类型的protobuf数据
 *  参考帖子 https://blog.csdn.net/hami700100332/article/details/80457548
 * */
public class ProtobufClientHandler extends SimpleChannelInboundHandler<DataInfo.MyMessage>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,DataInfo.MyMessage msg) throws  Exception{

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)throws Exception{
        int  i=new Random().nextInt(3)+1;
        System.out.println(i);
        DataInfo.MyMessage msg=null;
        if(i==1){
            msg=DataInfo.MyMessage.newBuilder()
                    .setDataType(DataInfo.MyMessage.DataType.PeopleType)
                    .setPeople(DataInfo.People.newBuilder().setId(1).setEmail("101@163.com").setName("我是人类").build())
                    .build();
        }else if(i==2){
            msg=DataInfo.MyMessage.newBuilder()
                    .setDataType(DataInfo.MyMessage.DataType.CatType)
                    .setCat(DataInfo.Cat.newBuilder().setName("我是一只猫").setCity("北京").build())
                    .build();

        }else if(i==3){
            msg=DataInfo.MyMessage.newBuilder()
                    .setDataType(DataInfo.MyMessage.DataType.DogType)
                    .setDog(DataInfo.Dog.newBuilder().setName("我是一条狗").setAge(5).build())
                    .build();
        }
        ctx.channel().writeAndFlush(msg);

    }

}

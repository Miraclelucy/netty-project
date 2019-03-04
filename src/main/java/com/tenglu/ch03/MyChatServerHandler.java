package com.tenglu.ch03;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 *  @author lushiqin 20190221
 * 《Netty源码解析-张龙》第8讲 多客户端连接的服务器端
 *
 * */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    // 1 用来保存多个channel对象的变量~~~~~~~~~~~注意这里是静态变量
    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"  "+msg);
        //1 获取channel
        Channel channel=ctx.channel();
        channelGroup.forEach(ch-> {
            //2 逻辑判断~~~~~~~~~~~非本通道打印传递过来的消息，本通道打印自己
            if(ch !=channel){
                ch.writeAndFlush("来自"+channel.remoteAddress()+"的消息:"+msg+"\n");
            }
            else{
                //3 ~~~~~~~~~~~最后不加上\n，居然打印不出来，下次才能flush出来
                ch.writeAndFlush("自己"+"\n" );
            }
        });


    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //----处理多客户端连接的逻辑----
        //1 获取channel
        Channel channel=ctx.channel();

        //2 广播消息：将指定的消息写到这个组的所有channel
        channelGroup.writeAndFlush("服务器"+channel.remoteAddress()+"加入\n");
        //3 加入channel
        channelGroup.add(channel);


    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //----处理多客户端连接的逻辑----
        //1 获取channel
        Channel channel=ctx.channel();
        //2 广播消息：将指定的消息写到这个组的所有channel
        channelGroup.writeAndFlush("服务器"+channel.remoteAddress()+"离开\n");
        //3 移除channel （netty会自动移除,可省略该行）
        //channelGroup.remove(channel);
        //4 打印channelGroup的长度，查看netty是否自动删除了该channel
        System.out.println(channelGroup.size());

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        System.out.println("服务器"+channel.remoteAddress()+"上线\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        System.out.println("服务器"+channel.remoteAddress()+"下线\n");
    }



    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }


}
package com.tenglu.ch00.nionetty;

import com.tenglu.ch00.nionetty.pool.Boss;
import com.tenglu.ch00.nionetty.pool.NioSelectorRunnablePool;

import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;

/**
 *  @author lushiqin 20190129
 * 《Netty快速入门教程-第四课 Netty源码分析》
 *
 * */
public class ServerBootstrap {

    private NioSelectorRunnablePool selectorRunnablePool;

    public ServerBootstrap(NioSelectorRunnablePool selectorRunnablePool) {
        this.selectorRunnablePool = selectorRunnablePool;
    }

    /**
     * 绑定端口
     * @param localAddress
     */
    public void bind(final SocketAddress localAddress){
        try {
            //1  获得一个服务端通道
            ServerSocketChannel serverChannel = ServerSocketChannel.open();

            //2  设置通道参数
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(localAddress);

            //3 获取一个boss选择器
            Boss nextBoss = selectorRunnablePool.nextBoss();

            //4 向boss选择器注册这个服务端通道
            nextBoss.acceptChannelTask(serverChannel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

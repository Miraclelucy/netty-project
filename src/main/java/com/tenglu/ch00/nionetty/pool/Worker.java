package com.tenglu.ch00.nionetty.pool;


import java.nio.channels.SocketChannel;

/**
 *  @author lushiqin 20190129
 * 《Netty快速入门教程-第四课 Netty源码分析》
 *
 * */
public interface Worker {
    public void registerChannelTask(SocketChannel socketChannel);

}

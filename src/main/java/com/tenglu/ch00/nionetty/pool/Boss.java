package com.tenglu.ch00.nionetty.pool;

import java.nio.channels.ServerSocketChannel;

/**
 *  @author lushiqin 20190128
 * 《Netty快速入门教程-第二课 Netty服务端》
 *
 * */

public interface Boss {
    public void acceptChannelTask(ServerSocketChannel serverSocketChannel);
}

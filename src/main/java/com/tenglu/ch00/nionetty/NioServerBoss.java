package com.tenglu.ch00.nionetty;


import com.tenglu.ch00.nionetty.pool.Boss;
import com.tenglu.ch00.nionetty.pool.NioSelectorRunnablePool;
import com.tenglu.ch00.nionetty.pool.Worker;

import java.io.IOException;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;

/**
 *  @author lushiqin 20190129
 * 《Netty快速入门教程-第四课 Netty源码分析》
 *
 * */
public class NioServerBoss extends AbstractNioSelector implements Boss {

    //1 构造函数
    public NioServerBoss(Executor executor, String threadName, NioSelectorRunnablePool selectorRunnablePool) {
        super(executor, threadName, selectorRunnablePool);
    }

    //2 重写方法
    @Override
    protected void process(Selector selector) throws IOException {
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        if (selectedKeys.isEmpty()) {
            return;
        }

        for (Iterator<SelectionKey> i = selectedKeys.iterator(); i.hasNext();) {
            SelectionKey key = i.next();
            i.remove();
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            //1 获取新客户端通道
            SocketChannel channel = server.accept();
            //2 设置客户端通道参数
            channel.configureBlocking(false);
            //3 获取一个worker选择器
            Worker nextworker = getSelectorRunnablePool().nextWorker();
            //4 向worker选择器注册这个客户端通道
            nextworker.registerChannelTask(channel);

            System.out.println("新客户端链接");
        }
    }


    //3 重写方法
    @Override
    protected int select(Selector selector) throws IOException {
        return selector.select();
    }

    //4 实现接口中的方法
    public void acceptChannelTask(final  ServerSocketChannel serverChannel) {
        final Selector selector = this.selector;
        registerTask(new Runnable() {
            @Override
            public void run() {
                try {
                    //注册serverChannel到selector
                    serverChannel.register(selector, SelectionKey.OP_ACCEPT);
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

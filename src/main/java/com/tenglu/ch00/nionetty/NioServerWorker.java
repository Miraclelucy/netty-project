package com.tenglu.ch00.nionetty;


import com.tenglu.ch00.nionetty.pool.NioSelectorRunnablePool;
import com.tenglu.ch00.nionetty.pool.Worker;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;

/**
 *  @author lushiqin 20190129
 * 《Netty快速入门教程-第四课 Netty源码分析》
 *
 * */
public  class NioServerWorker extends AbstractNioSelector implements Worker{

    //1 构造函数
    public NioServerWorker(Executor executor, String threadName, NioSelectorRunnablePool selectorRunnablePool) {
        super(executor, threadName, selectorRunnablePool);
    }

    //2 重写方法
    @Override
    protected void process(Selector selector) throws IOException {
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        if (selectedKeys.isEmpty()) {
            return;
        }
        Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
        while (ite.hasNext()) {
            SelectionKey key = (SelectionKey) ite.next();
            // 移除，防止重复处理
            ite.remove();

            // 得到事件发生的Socket通道
            SocketChannel channel = (SocketChannel) key.channel();

            // 数据总长度
            int ret = 0;
            boolean failure = true;
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //读取数据
            try {
                ret = channel.read(buffer);
                failure = false;
            } catch (Exception e) {
                // ignore
            }
            //判断是否连接已断开
            if (ret <= 0 || failure) {
                key.cancel();
                System.out.println("客户端断开连接");
            }else{
                System.out.println("收到来自"+channel.socket().getRemoteSocketAddress()+"的数据:" + new String(buffer.array()));

                //回写数据
                ByteBuffer outBuffer = ByteBuffer.wrap("收到\n".getBytes());
                channel.write(outBuffer);// 将消息回送给客户端
            }
        }
    }

    //3 重写方法
    @Override
    protected int select(Selector selector) throws IOException {
        return selector.select(500);
    }


    //4 实现接口中的方法
    public void registerChannelTask(final SocketChannel channel){
        final Selector selector = this.selector;
        registerTask(new Runnable() {
            @Override
            public void run() {
                try {
                    //将客户端注册到selector中
                    channel.register(selector, SelectionKey.OP_READ);
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}

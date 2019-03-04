package com.tenglu.ch00.nionetty;


import com.tenglu.ch00.nionetty.pool.NioSelectorRunnablePool;

import java.io.IOException;
import java.nio.channels.Selector;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *  @author lushiqin 20190129
 * 《Netty快速入门教程-第四课 Netty源码分析》
 *
 * */
public abstract class AbstractNioSelector implements Runnable {

    private final Executor executor; //线程池
    private String threadName;//线程名称
    protected NioSelectorRunnablePool selectorRunnablePool;//线程管理对象

    protected Selector selector; //选择器
    protected final AtomicBoolean wakenUp = new AtomicBoolean();//选择器wakenUp状态标记
    private final Queue<Runnable> taskQueue = new ConcurrentLinkedQueue<Runnable>();//任务队列


    AbstractNioSelector(Executor executor, String threadName, NioSelectorRunnablePool selectorRunnablePool) {
        this.executor = executor;
        this.threadName = threadName;
        this.selectorRunnablePool = selectorRunnablePool;
        openSelector();
    }


    /**
     * 开启selector
     */
    public void  openSelector(){
        try {
            this.selector=Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        executor.execute(this);

    }


    public void run() {
        Thread.currentThread().setName(this.threadName);

        while (true) {
            try {
                wakenUp.set(false);

                select(selector);

                processTaskQueue();

                process(selector);
            } catch (Exception e) {
                // ignore
            }
        }

    }


    /**
     * 注册一个任务并激活selector
     *
     * @param task
     */
    protected final void registerTask(Runnable task) {
        taskQueue.add(task);

        Selector selector = this.selector;

        if (selector != null) {
            if (wakenUp.compareAndSet(false, true)) {
                selector.wakeup();
            }
        } else {
            taskQueue.remove(task);
        }
    }

    /**
     * 执行队列里的任务
     */
    private void processTaskQueue() {
        for (;;) {
            final Runnable task = taskQueue.poll();
            if (task == null) {
                break;
            }
            task.run();
        }
    }

    /**
     * 获取线程管理对象
     * @return
     */
    public NioSelectorRunnablePool getSelectorRunnablePool() {
        return selectorRunnablePool;
    }

    protected abstract int select(Selector selector) throws IOException;

    protected abstract void process(Selector selector) throws IOException;
}


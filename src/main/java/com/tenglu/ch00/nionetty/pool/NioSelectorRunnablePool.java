package com.tenglu.ch00.nionetty.pool;


import com.tenglu.ch00.nionetty.NioServerBoss;
import com.tenglu.ch00.nionetty.NioServerWorker;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  @author lushiqin 20190129
 * 《Netty快速入门教程-第四课 Netty源码分析》
 *  selector线程池
 * */
public class NioSelectorRunnablePool {

    private AtomicInteger bossIndex= new AtomicInteger();
    private Boss[] bosses;

    private AtomicInteger workerIndex= new AtomicInteger();
    private Worker[] workers;


    //1 初始化线程
    public NioSelectorRunnablePool(Executor boss,Executor worker){
        //初始化boss线程
        InitBoss(boss,1);
        //初始化worker线程
        InitWorker(worker, Runtime.getRuntime().availableProcessors() * 2);
    }



    public void InitBoss(Executor boss,int count){
        this.bosses = new NioServerBoss[count];
        for (int i = 0; i < bosses.length; i++) {
            bosses[i] = new NioServerBoss(boss, "boss thread " + (i+1), this);
        }

    }

    public void InitWorker(Executor worker,int count){

        this.workers = new NioServerWorker[count];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new NioServerWorker(worker, "worker thread " + (i+1), this);
        }

    }

    //2 获取下一个boss线程

    public Boss  nextBoss(){
        return bosses[Math.abs(bossIndex.getAndIncrement() % bosses.length)];
    }


    //3 获取下一个worker线程

    public Worker  nextWorker(){
        return workers[Math.abs(workerIndex.getAndIncrement() % workers.length)];
    }

}

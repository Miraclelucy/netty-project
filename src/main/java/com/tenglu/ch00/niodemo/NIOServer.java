package com.tenglu.ch00.niodemo;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *  @author lushiqin 20190127
 * 《Netty快速入门教程-第一课NIO》NIO
 * ServerSocketChannel相当于餐厅前台
 * SocketChannel相当于餐厅服务员
 *
 * */
public class NIOServer {
    private int size=1024;
    private ByteBuffer sendbuffer=ByteBuffer.allocate(size);
    private ByteBuffer receivebuffer=ByteBuffer.allocate(size);
    private Selector selector;



    /**
     * 初始化服务端
     * @throws IOException
     */
    public NIOServer() throws IOException{

        //1 获取一个服务端通道serverSocketChannel
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        //2 设置服务端通道的相关参数：非阻塞、端口绑定
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1",10101));
        //3 将服务端通道注册到选择器
        selector=Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    /**
     * 监听
     */
    public  void listen() throws  IOException{
        System.out.println("服务端启动成功！");
        // 轮询Selector轮询就绪的key(通道)
        while(true){
            selector.select();
            //1 获取所有需要处理的selectionKeys
            Set<SelectionKey> selectionKeys=selector.selectedKeys();
            //2 创建迭代器
            Iterator<SelectionKey> iterator =selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey=iterator.next();
                //3 取到selectionKey后，删除之
                iterator.remove();
                //4 业务逻辑
                handle(selectionKey);
            }


        }

    }

    public void handle(SelectionKey selectionKey) throws  IOException{
        if(selectionKey.isAcceptable()){
            handleAccept(selectionKey);
        }else if(selectionKey.isReadable()){
            handleRead(selectionKey);
        }
    }

    public void handleAccept(SelectionKey selectionKey) throws  IOException {
        //1 获取一个服务端通道serverSocketChannel
        ServerSocketChannel serverSocketChannel=(ServerSocketChannel)selectionKey.channel();
        //2 获得一个客服端通道
        SocketChannel socketChannel=serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        //3 将客服端通道注册到选择器
        socketChannel.register(selector,SelectionKey.OP_READ);

    }



    public void handleRead(SelectionKey selectionKey) throws  IOException{
        //1 获取一个客服端通道serverSocketChannel
        SocketChannel socketChannel=(SocketChannel)selectionKey.channel();
        receivebuffer.clear();
        //2 缓冲区来读取数据
        int read=socketChannel.read(receivebuffer);
        if(read >0){

            String str =new String(receivebuffer.array(),0,read);
            System.out.print(str);

            //回写数据
            sendbuffer.clear();
            sendbuffer.put("hello,i am server".getBytes());
            sendbuffer.flip();//使用buffer时这一步一定要
            socketChannel.write(sendbuffer);
        }

        //

    }

    public static void main(String[] args) throws  IOException{
        NIOServer server=new NIOServer();
        server.listen();
    }

}

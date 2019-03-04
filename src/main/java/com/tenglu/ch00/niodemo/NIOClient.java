package com.tenglu.ch00.niodemo;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *  @author lushiqin 20190127
 * 《Netty快速入门教程-第一课NIO》NIO客服端
 *
 * */
public class NIOClient {
    private int size=1024;
    private ByteBuffer sendbuffer=ByteBuffer.allocate(size);
    private ByteBuffer receivebuffer=ByteBuffer.allocate(size);
    private Selector selector;



    /**
     * 初始化客服端
     * @throws IOException
     */
    public NIOClient() throws IOException{

        //1 获取一个客服端通道socketChannel
        SocketChannel socketChannel=SocketChannel.open();
        //2 设置客服端通道的相关参数：非阻塞、端口连接
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",10101));
        //3 将客服端通道注册到选择器
        selector=Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

    }

    /**
     * 监听
     */
    public  void listen() throws  IOException{
        System.out.println("客服端启动成功！");
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
        if(selectionKey.isConnectable()){
            handleConnect(selectionKey);
        }else if(selectionKey.isReadable()){
            handleRead(selectionKey);
        }/*else if(selectionKey.isWritable()){
            handleWrite(selectionKey);
        }*/
    }

    public void handleConnect(SelectionKey selectionKey) throws  IOException {
        //1 获取一个客服端通道serverSocketChannel
        SocketChannel socketChannel=(SocketChannel)selectionKey.channel();

        if(socketChannel.isConnectionPending()){
            //2 完成连接
            socketChannel.finishConnect();
            System.out.println("客服端完成连接");
            //3 发送数据
            sendbuffer.clear();
            sendbuffer.put("hello ,i am clientB".getBytes());
            sendbuffer.flip(); //使用buffer时这一步一定要
            socketChannel.write(sendbuffer);
        }
        socketChannel.register(selector,selectionKey.OP_READ);

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

        }
        socketChannel.register(selector,selectionKey.OP_WRITE);
    }


    public void handleWrite(SelectionKey selectionKey) throws  IOException{
        //1 获取一个客服端通道serverSocketChannel
        SocketChannel socketChannel=(SocketChannel)selectionKey.channel();
        sendbuffer.clear();
        //2 往服务端写数据
        sendbuffer.put("ok,i get it ！from client".getBytes());
        sendbuffer.flip();//使用buffer时这一步一定要
        socketChannel.write(sendbuffer);

        socketChannel.register(selector,selectionKey.OP_READ);
    }


    public static void main(String[] args) throws  IOException{
        NIOClient client=new NIOClient();
        client.listen();
    }

}

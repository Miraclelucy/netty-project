package com.tenglu.ch09.thriftrpc;

import com.tenglu.ch09.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 *  @author lushiqin 20190306
 * 《Netty源码解析-张龙》第18讲 Apache Thrift应用详解与实例剖析
 * */
public class ThriftServer {


    public static void main(String[] args) throws  Exception{
        //1 thrift的服务器
        TNonblockingServerSocket socket=new TNonblockingServerSocket(8899);
        THsHaServer.Args arg=new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);

        PersonService.Processor<PersonServerImpl> processor=new PersonService.Processor<>(new PersonServerImpl());

        arg.protocolFactory(new TCompactProtocol.Factory());//2 thrift文件格式
        arg.transportFactory(new TFramedTransport.Factory()); //3 thrift传输方式
        arg.processorFactory(new TProcessorFactory(processor));//

        TServer server=new THsHaServer(arg);

        System.out.println("Thrift Server Start!");

        server.serve();
    }

}

package com.tenglu.ch09.thriftrpc;

import com.tenglu.ch09.Person;
import com.tenglu.ch09.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;


/**
 *  @author lushiqin 20190306
 * 《Netty源码解析-张龙》第18讲 Apache Thrift应用详解与实例剖析
 * */

public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport=new TFramedTransport(new TSocket("localhost",8899),600);
        TProtocol protocol=new TCompactProtocol(transport);

        PersonService.Client client=new PersonService.Client(protocol);

        try{
            transport.open();

            //调用client，实现业务逻辑。
            //1 获取用户
            Person p=client.getPersonByName("扎三");
            System.out.println("the result from rpc server:");
            System.out.println(p.getAge());
            System.out.println(p.getName());
            System.out.println(p.isMarried());

            //2 保存用户
            Person p2=new Person();
            p2.setAge(22);
            p2.setName("李四");
            p2.setMarried(true);
            client.savePerson(p2);



        }catch (Exception e){
            e.printStackTrace();
        }

        finally {
            transport.close();
        }

    }
}

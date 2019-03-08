package com.tenglu.ch09.thriftrpc;

import com.tenglu.ch09.MyException;
import com.tenglu.ch09.Person;
import com.tenglu.ch09.PersonService;
import org.apache.thrift.TException;

/**
 *  @author lushiqin 20190306
 * 《Netty源码解析-张龙》第18讲 Apache Thrift应用详解与实例剖析
 * */
public class PersonServerImpl implements PersonService.Iface {


    @Override
    public Person getPersonByName(String name) throws MyException, TException {

        System.out.println("client invoke :getPersonByName： "+name);
        Person p=new Person();
        p.setAge(12);
        p.setName("扎三");
        p.setMarried(false);
        return p;
    }

    @Override
    public void savePerson(Person person) throws MyException, TException {
        System.out.println("client invoke :savePerson: " );
        System.out.println(person.getAge());
        System.out.println(person.getName() );
        System.out.println(person.isMarried() );

    }
}

package com.tenglu.ch06;


/**
 *  @author lushiqin 20190226
 * 《Netty源码解析-张龙》第14讲 Protobuf完整实例--在同一个jvm中通信
 *
 * */
public class ProtobufTest {

    public static void main(String[] args) throws  Exception{
        //A服务器产生对象
        StudentInfo.Student student= StudentInfo.Student.newBuilder()
                .setId(1).setEmail("zhangsan@163.com").setName("张三")
                .build();
        byte[] student2byteArray=student.toByteArray();
        //B服务器接收到字节数组后，将字节数组转换成对象
        StudentInfo.Student student2=StudentInfo.Student.parseFrom(student2byteArray);
        System.out.println(student2.getEmail());
        System.out.println(student2.getName());
        System.out.println(student2.getId());

    }

}

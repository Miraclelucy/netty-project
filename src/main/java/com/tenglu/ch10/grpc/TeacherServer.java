package com.tenglu.ch10.grpc;

import com.tenglu.ch10.GreeterGrpc;
import com.tenglu.ch10.HelloReply;
import com.tenglu.ch10.HelloRequest;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.logging.Logger;


/**
 *  @author lushiqin 20190309
 * 《Netty源码解析-张龙》第21-28讲  gRPC实践及4种形式的客户端与服务器通信方式
 *  1 客户端发送一个请求，服务端返回一个响应
 *  2 客户端发送一个请求，服务端返回流（a sequence of messages ）--多半以迭代器的形式呈现
 *  3 客户端发送流，服务端返回一个响应
 *  4 客户端发送流，服务端返回流
 * */

public class TeacherServer {

    private static final Logger logger = Logger.getLogger(TeacherServer.class.getName());

    private Server server;

    private void start() throws IOException {
    /* The port on which the server should run */
        int port = 50051;
        //1 创建服务对象
        server = ServerBuilder.forPort(port)
                .addService(new GreeterImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);

        //2 回调钩子
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                TeacherServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final TeacherServer server = new TeacherServer();
        server.start();
        server.blockUntilShutdown();
    }

    static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        @Override
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void sayHelloAgain(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello again " + req.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}

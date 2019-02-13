package com.xhMall.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * Created by sheting on Administrator
 * DateTime  2019/2/13,21:35
 */
public class NettyServer {
    public static void main(String[] args) {
        /**
         * nio 服务器引导程序
         * 引导我们进行服务端的启动工作
         */
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        /**
         * boss 表示监听端口 接收新连接的线程组 一般情况下 接口线程组配置一个线程即可 ，
         * NioEventLoopGroup 默认的个数为 CPU核数*2
         */
        NioEventLoopGroup bossGroup  = new NioEventLoopGroup(1);

        /**
         * worker 对应 IOServer 中负责读取数据的线程 ， 主要用于数据读取语句业务逻辑处理 。
         *        表示处理每一条连接上的数据读写的线程组
         */
        NioEventLoopGroup workerGroup  = new NioEventLoopGroup();

        serverBootstrap
            .group(bossGroup, workerGroup )
                /**
                 * 指定服务端的IO模型为 NIO ,serverBootstrap.channel(NioServerSocketChannel.class)
                 * 指定IO 模型为 BIO , 那么这里配置OioServerSocketChannel.class类型即可
                 */
                .channel(NioServerSocketChannel.class)
                /**
                 * 引导类创建一个ChannelInitializer
                 * 定义每条连接的数据读写、业务处理逻辑
                 * NioServerSocketChannel 和NioSocketChannel的概念相当于BIO 模型中 ServerSocket 和Socket .
                 */
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                .bind(18868)
                .addListener(new GenericFutureListener <Future <? super Void>>() {
                    @Override
                    public void operationComplete(Future <? super Void> future) throws Exception {
                        if (future.isSuccess()){
                            System.out.println("绑定端口成功");
                        }else{
                            System.out.println("端口绑定失败");
                            bind(serverBootstrap,18869);
                        }
                    }
                });
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
}

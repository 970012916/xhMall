package com.xhMall.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by sheting on Administrator
 * DateTime  2019/2/13,21:43
 */
public class NettyClient {
    public static final int MAX_RETRY = 5;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();

        /**
         * 在客户端程序中 ， group 对应了我们IOClient 中 新起的线程。
         */
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap
                /**
                 * 1.指定线程模型
                 */
                .group(group)
                /**
                 * 2.指定 IO 类型为 NIO
                 */
                .channel(NioSocketChannel.class)
                /**
                 * 3.IO 处理逻辑
                 */
                .handler(new ChannelInitializer <Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });
        /**
         * 4.建立连接
         */
        Channel channel = connect(bootstrap,"127.0.0.1", 18868,5);

        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }
    }

    private static Channel connect(Bootstrap bootstrap, String host, int port, int retry) {
        return bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap
                    //这个方法返回的是 BootstrapConfig，他是对 Bootstrap 配置参数的抽象
                    .config()
                    //返回的就是我们在一开始的时候配置的线程模型 workerGroup
                    .group()
                    //实现定时任务逻辑
                    .schedule(
                        () -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS
                    );
            }
        }).channel();
    }
}
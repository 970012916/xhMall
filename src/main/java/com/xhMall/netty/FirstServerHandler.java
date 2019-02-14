package com.xhMall.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * Created by sheting on Administrator
 * DateTime  2019/2/14,23:09
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 这个方法在接收到客户端发来的数据之后被回调
     * @param ctx
     * @param msg Netty 里面数据读写的载体
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));

        // 回复数据到客户端
        System.out.println(new Date() + ": 服务端写出数据");
        String str = "你好，欢迎关注我的微信公众号，《闪电侠的博客》!";
        ByteBuf out = getByteBuf(ctx,str);
        ctx.channel().writeAndFlush(out);

    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx,String str) {
        byte[] bytes = str.getBytes(Charset.forName("utf-8"));

        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(bytes);

        return buffer;
    }

    /**
     * 客户端连接后主动调用
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 回复数据到客户端
        System.out.println(new Date() + ": 服务端主动通知连接的客户端");
        String str = "欢迎撩骚，客户端";
        ByteBuf out = getByteBuf(ctx,str);
        ctx.channel().writeAndFlush(out);
    }
}

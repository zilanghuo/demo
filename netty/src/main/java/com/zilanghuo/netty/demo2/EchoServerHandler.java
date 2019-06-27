package com.zilanghuo.netty.demo2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @Description : todo
 * @Author :      laiwufa
 * @Date :        2019/6/25 16:18
 */
public class EchoServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        // 针对某个channel 进行发送消息
        System.out.println("channelId:" + ctx.channel().id().asLongText());
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("Response from server. You have input \"" + in.toString(CharsetUtil.UTF_8) + "\"!", CharsetUtil.UTF_8));
    //    ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelId:" + ctx.channel().id().asLongText() + "- is closed");
        ctx.fireChannelInactive();
    }
}

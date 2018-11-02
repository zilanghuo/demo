package com.zilanghuo.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author laiwufa
 * @date 2018/11/2
 * use:
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // 1
        System.out.println("接收到客户端请求："+msg);
        String request = (String) msg; //2
        String response = null;
        if ("QUERY TIME ORDER".equals(request)) { // 3
            response = new Date(System.currentTimeMillis()).toString();
        } else {
            response = "BAD REQUEST";
        }
        response = response + System.getProperty("line.separator"); // 4
        ByteBuf resp = Unpooled.copiedBuffer(response.getBytes()); // 5
        System.out.println("返回客户端数据："+response);
        ctx.writeAndFlush(resp); // 6

    }
}

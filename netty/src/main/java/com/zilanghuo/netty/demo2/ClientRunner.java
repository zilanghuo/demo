package com.zilanghuo.netty.demo2;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @Description : todo
 * @Author :      laiwufa
 * @Date :        2019/6/25 16:20
 */
public class ClientRunner {

    public static void main(String[] args) throws Exception {
        new ClientRunner().start();
    }

    public void start() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress("127.0.0.1", 9090))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(
                                    new EchoClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync();
            if (f.channel().isActive()) {
                f.channel().writeAndFlush(Unpooled.copiedBuffer("Hello Casper!", CharsetUtil.UTF_8));
            }
            Thread.sleep(1000 * 2);
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}

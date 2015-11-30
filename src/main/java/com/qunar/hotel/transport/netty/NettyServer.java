package com.qunar.hotel.transport.netty;

import com.qunar.hotel.bootstrap.Configuration;
import com.qunar.hotel.manager.ServerLifeCycleListener;
import com.qunar.hotel.transport.Server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by hang.gao on 2015/6/8.
 */
public class NettyServer implements Server {

    private final Configuration configuration;

    private final ServerLifeCycleListener listener;

    private Channel ch;

    public NettyServer(Configuration configuration,
                       ServerLifeCycleListener listener) {
        this.configuration = configuration;
        this.listener = listener;
    }

    public void start() {

        // Configure the server.
        EventLoopGroup bossGroup   = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 1024);
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .handler(new LoggingHandler(LogLevel.INFO))
             .childHandler(new NettyServerInitializer(configuration));

            ch = b.bind(configuration.getPort())
                  .sync()
                  .channel();
            listener.afterServerStart();
            ch.closeFuture()
              .sync();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void stop() {
        if (ch == null) {
            return;
        }
        try {
            listener.beforeServerStop();
            ch.close();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

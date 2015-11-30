package com.qunar.hotel.transport.netty;

import com.qunar.hotel.bootstrap.Configuration;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Created by hang.gao on 2015/6/8.
 */
public class NettyServerInitializer extends ChannelInitializer {

    private final Configuration configuration;

    public NettyServerInitializer(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        EventExecutorGroup exec = new DefaultEventExecutorGroup(configuration.getApplicationThreadCount());

        ch.pipeline().addLast(exec, new HttpServerCodec(configuration.getMaxInitialLineLength(),
                                                        configuration.getMaxheaderSize(),
                                                        configuration.getMaxChunkSize()));
        ch.pipeline().addLast(exec, new NettyChannelHandler(configuration.getRequestHandler()));
    }
}

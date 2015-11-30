package com.qunar.hotel.transport.netty;

import com.qunar.hotel.bootstrap.Configuration;
import com.qunar.hotel.manager.ServerLifeCycleListener;
import com.qunar.hotel.transport.Server;
import com.qunar.hotel.transport.ServerFactory;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class NettyServerFactory implements ServerFactory {
    public Server createServer(Configuration configuration,
                               ServerLifeCycleListener listener) {
        return new NettyServer(configuration, listener);
    }
}

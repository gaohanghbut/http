package cn.yxffcode.http.transport.netty;

import cn.yxffcode.http.manager.ServerLifeCycleListener;
import cn.yxffcode.http.transport.Server;
import cn.yxffcode.http.transport.ServerFactory;
import cn.yxffcode.http.bootstrap.Configuration;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class NettyServerFactory implements ServerFactory {
    public Server createServer(Configuration configuration,
                               ServerLifeCycleListener listener) {
        return new NettyServer(configuration, listener);
    }
}

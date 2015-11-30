package cn.yxffcode.http.transport;

import cn.yxffcode.http.manager.ServerLifeCycleListener;
import cn.yxffcode.http.bootstrap.Configuration;

/**
 * Created by hang.gao on 2015/6/9.
 */
public interface ServerFactory {
    Server createServer(Configuration configuration,
                        ServerLifeCycleListener listener);
}

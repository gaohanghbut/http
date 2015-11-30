package com.qunar.hotel.transport;

import com.qunar.hotel.bootstrap.Configuration;
import com.qunar.hotel.manager.ServerLifeCycleListener;

/**
 * Created by hang.gao on 2015/6/9.
 */
public interface ServerFactory {
    Server createServer(Configuration configuration, ServerLifeCycleListener listener);
}

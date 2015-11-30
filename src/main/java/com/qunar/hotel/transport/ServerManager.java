package com.qunar.hotel.transport;

import com.qunar.hotel.bootstrap.Configuration;

/**
 * Created by hang.gao on 2015/6/10.
 */
public interface ServerManager {
    void serverStart(Configuration configuration);

    void serverStop();
}

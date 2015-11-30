package com.qunar.hotel.bootstrap;

import com.qunar.hotel.transport.RequestHandler;

/**
 * Created by hang.gao on 2015/6/8.
 */
public interface Configuration {

    int getPort();

    int getMaxInitialLineLength();

    int getMaxheaderSize();

    int getMaxChunkSize();

    int getApplicationThreadCount();

    RequestHandler getRequestHandler();

}

package cn.yxffcode.http.bootstrap;

import cn.yxffcode.http.transport.RequestHandler;

/**
 * Created by hang.gao on 2015/6/8.
 */
public interface Configuration {

    int getPort();

    int getMaxInitialLineLength();

    int getMaxHeaderSize();

    int getMaxChunkSize();

    int getApplicationThreadCount();

    RequestHandler getRequestHandler();

}

package cn.yxffcode.http.transport;

import cn.yxffcode.http.bootstrap.Configuration;

/**
 * Created by hang.gao on 2015/6/10.
 */
public interface ServerManager {
    void serverStart(Configuration configuration);

    void serverStop();
}

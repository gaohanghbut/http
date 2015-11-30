package cn.yxffcode.http.api;

import java.io.IOException;

/**
 * Created by hang.gao on 2015/6/8.
 */
public interface WebComponent {

    void init();

    void onRequest(RequestContext ctx) throws IOException;

    void destroy();
}

package cn.yxffcode.http.transport;

import cn.yxffcode.http.api.RequestContext;

import java.io.IOException;

/**
 * Created by hang.gao on 2015/6/8.
 */
public interface RequestHandler {

    void handleRequest(RequestContext ctx) throws IOException;
}

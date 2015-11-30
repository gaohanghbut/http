package cn.yxffcode.http.proxy;

import cn.yxffcode.http.api.RequestContext;

import java.io.IOException;

/**
 * Created by hang.gao on 2015/6/8.
 */
public interface Invoker {

    void invoke(RequestContext ctx) throws IOException;
}

package cn.yxffcode.http.proxy.chain;

import cn.yxffcode.http.api.RequestContext;

import java.io.IOException;

/**
 * Created by hang.gao on 2015/6/8.
 */
public interface Filter {

    boolean doFilter(RequestContext ctx) throws IOException;
}

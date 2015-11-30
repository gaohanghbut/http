package cn.yxffcode.http.proxy.chain.impl;

import cn.yxffcode.http.proxy.chain.Filter;
import cn.yxffcode.http.api.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hang.gao on 2015/6/8.
 */
public class AccessLogFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger("accessLog");

    public boolean doFilter(RequestContext ctx) {
        logger.info("{}:{}", ctx.getRemoteAddress(), ctx.getRequestUri());
        return true;
    }
}

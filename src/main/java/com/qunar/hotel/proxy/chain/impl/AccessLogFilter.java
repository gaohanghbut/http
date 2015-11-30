package com.qunar.hotel.proxy.chain.impl;

import com.qunar.hotel.api.RequestContext;
import com.qunar.hotel.proxy.chain.Filter;
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

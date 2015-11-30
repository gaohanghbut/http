package com.qunar.hotel.proxy.chain.impl;

import com.qunar.hotel.api.RequestContext;
import com.qunar.hotel.proxy.chain.Filter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class AccessLimitFilter implements Filter {
    private Map<String, Long> limitMap = new ConcurrentHashMap<String, Long>();

    public boolean doFilter(RequestContext ctx) throws IOException {
        Long time = limitMap.get(ctx.getRemoteAddress());
        if (time == null) {
            limitMap.put(ctx.getRemoteAddress(), System.currentTimeMillis());
        } else {
            long delta = System.currentTimeMillis() - limitMap.get(ctx.getRemoteAddress());
            if (delta < 2000) {
                ctx.error(403, "forbidden");
                return false;
            } else {
                limitMap.remove(ctx.getRemoteAddress());
            }
        }
        return true;
    }
}

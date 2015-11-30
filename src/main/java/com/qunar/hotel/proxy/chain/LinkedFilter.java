package com.qunar.hotel.proxy.chain;

import com.qunar.hotel.api.RequestContext;

import java.io.IOException;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class LinkedFilter implements Filter {
    private final Filter current;
    private final Filter next;

    public LinkedFilter(Filter current, Filter next) {
        this.current = current;
        this.next = next;
    }

    public boolean doFilter(RequestContext ctx) throws IOException {
        if (current.doFilter(ctx)) {
            return next.doFilter(ctx);
        }
        return false;
    }
}

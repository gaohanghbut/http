package com.qunar.hotel.proxy.chain;

import com.qunar.hotel.api.RequestContext;
import com.qunar.hotel.proxy.Invoker;

import java.io.IOException;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class DelegateFilter implements Filter {
    private final Invoker invoker;

    public DelegateFilter(Invoker invoker) {
        this.invoker = invoker;
    }

    public boolean doFilter(RequestContext ctx) throws IOException {
        invoker.invoke(ctx);
        return true;
    }
}

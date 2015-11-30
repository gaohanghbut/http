package com.qunar.hotel.transport;

import com.qunar.hotel.api.RequestContext;
import com.qunar.hotel.common.ExtensionLoaders;
import com.qunar.hotel.proxy.EventInvoker;
import com.qunar.hotel.proxy.chain.DelegateFilter;
import com.qunar.hotel.proxy.chain.Filter;
import com.qunar.hotel.proxy.chain.LinkedFilter;
import com.qunar.hotel.proxy.CompositeInvoker;
import com.qunar.hotel.proxy.Invoker;

import java.io.IOException;
import java.util.List;

/**
 * Created by hang.gao on 2015/6/8.
 */
public class DefaultRequestHandler implements RequestHandler {

    private final Filter head;

    public DefaultRequestHandler() {
        List<Invoker> invokers = ExtensionLoaders.getExtensionLoader(Invoker.class).getExtensions();
        Invoker invoker;
        if (invokers.size() == 1) {
            invoker = new EventInvoker(invokers.get(0));
        } else {
            invoker = new EventInvoker(new CompositeInvoker(invokers));
        }
        List<Filter> filters = ExtensionLoaders.getExtensionLoader(Filter.class).getExtensions();
        Filter next = new LinkedFilter(new DelegateFilter(invoker), DoNothingFilter.INSTANCE);
        for (int i = filters.size() - 1, j = 0; i >= j; i--) {
            next = new LinkedFilter(filters.get(i), next);
        }
        head = next;
    }

    public void handleRequest(RequestContext ctx) throws IOException {
        head.doFilter(ctx);
    }

    private enum DoNothingFilter implements Filter {
        INSTANCE;

        public boolean doFilter(RequestContext ctx) {
            return true;
        }
    }
}

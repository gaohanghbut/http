package cn.yxffcode.http.transport;

import cn.yxffcode.http.proxy.CompositeInvoker;
import cn.yxffcode.http.proxy.EventInvoker;
import cn.yxffcode.http.api.RequestContext;
import cn.yxffcode.http.common.ExtensionLoaders;
import cn.yxffcode.http.proxy.Invoker;
import cn.yxffcode.http.proxy.chain.DelegateFilter;
import cn.yxffcode.http.proxy.chain.Filter;
import cn.yxffcode.http.proxy.chain.LinkedFilter;

import java.io.IOException;
import java.util.List;

/**
 * Created by hang.gao on 2015/6/8.
 */
public class DefaultRequestHandler implements RequestHandler {

    private final Filter head;

    public DefaultRequestHandler() {
        List<Invoker> invokers = ExtensionLoaders.getExtensionLoader(Invoker.class)
                                                 .getExtensions();
        Invoker       invoker;
        if (invokers.size() == 1) {
            invoker = new EventInvoker(invokers.get(0));
        } else {
            invoker = new EventInvoker(new CompositeInvoker(invokers));
        }
        List<Filter> filters = ExtensionLoaders.getExtensionLoader(Filter.class)
                                               .getExtensions();
        Filter       next    = new LinkedFilter(new DelegateFilter(invoker), DoNothingFilter.INSTANCE);
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

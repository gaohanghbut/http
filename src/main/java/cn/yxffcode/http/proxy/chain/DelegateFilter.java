package cn.yxffcode.http.proxy.chain;

import cn.yxffcode.http.proxy.Invoker;
import cn.yxffcode.http.api.RequestContext;

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

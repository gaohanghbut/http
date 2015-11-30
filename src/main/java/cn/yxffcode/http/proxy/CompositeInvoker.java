package cn.yxffcode.http.proxy;

import cn.yxffcode.http.api.RequestContext;

import java.io.IOException;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class CompositeInvoker implements Invoker {

    private final Iterable<Invoker> invokers;

    public CompositeInvoker(Iterable<Invoker> invokers) {
        this.invokers = invokers;
    }

    public void invoke(RequestContext ctx) throws IOException {
        for (Invoker invoker : invokers) {
            invoker.invoke(ctx);
        }
    }
}

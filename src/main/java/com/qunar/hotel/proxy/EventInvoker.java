package com.qunar.hotel.proxy;

import com.qunar.hotel.api.RequestContext;
import com.qunar.hotel.common.ExtensionLoaders;
import com.qunar.hotel.manager.event.EventDispacher;

import java.io.IOException;

/**
 * Created by hang.gao on 2015/6/10.
 */
public class EventInvoker implements Invoker {
    private final Invoker targetInvoker;
    private final EventDispacher eventDispacher;

    public EventInvoker(Invoker targetInvoker) {
        this.targetInvoker = targetInvoker;
        this.eventDispacher = ExtensionLoaders.getExtensionLoader(EventDispacher.class).getExtension();
    }

    public void invoke(RequestContext ctx) throws IOException {
        eventDispacher.fireRequestStart(ctx);
        try {
            targetInvoker.invoke(ctx);
        } finally {
            eventDispacher.fireRequestEnd(ctx);
        }
    }
}
